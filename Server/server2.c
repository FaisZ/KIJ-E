#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<pthread.h>
#include<arpa/inet.h>

typedef struct nodeklien
{
	struct sockaddr_in alamatklien;
	char namaklien[1000];
	int client_socket_fd;
	pthread_t * ptr_thread_terima;
	struct nodeklien * next;
	struct nodeklien * prev;
	int number;
} nodeklien;

typedef struct linkedlistklien_t
{
	int jumlah;
	int number_now;
	nodeklien * depan;
	nodeklien * belakang;	
} linkedlistklien_t;

linkedlistklien_t simpanklien;

	void push_back(struct sockaddr_in alamatklien, 
			char namaklien[], 
			int client_socket_fd,  
			pthread_t * ptr_thread_terima)
	{
		if (simpanklien.jumlah==0)
		{
			simpanklien.depan = (nodeklien *)malloc(sizeof(nodeklien));
			simpanklien.depan->alamatklien = alamatklien;
			strcpy(simpanklien.depan->namaklien, namaklien);
			simpanklien.depan->client_socket_fd = client_socket_fd;
			simpanklien.depan->ptr_thread_terima = ptr_thread_terima;
			simpanklien.depan->next = NULL;
			simpanklien.depan->prev = NULL;
			simpanklien.depan->number = simpanklien.number_now;
			simpanklien.belakang = simpanklien.depan;
		}
		else
		{
			nodeklien * temp;
			temp = (nodeklien *)malloc(sizeof(nodeklien));
			temp->alamatklien = alamatklien;
			strcpy(temp->namaklien, namaklien);
			temp->client_socket_fd = client_socket_fd;
			temp->ptr_thread_terima = ptr_thread_terima;
			temp->next = NULL;
			temp->prev = simpanklien.belakang;
			temp->number = simpanklien.number_now;
			simpanklien.belakang->next = temp;
			simpanklien.belakang = temp;
		}
		simpanklien.jumlah++;
		simpanklien.number_now++;
	}
	void deleteAtNumber(int number)
	{
		if (simpanklien.jumlah==0) return;		
		else if (simpanklien.jumlah==1)
		{
			free(simpanklien.depan);
			simpanklien.depan = NULL;
			simpanklien.belakang = NULL;
			simpanklien.jumlah = 0;
		}
		else
		{
			nodeklien * temp;
			temp = simpanklien.depan;
			while (temp!=NULL && number!=temp->number)
			{
				temp = temp->next;
			}
			if (temp==simpanklien.depan)
			{
				temp->next->prev = NULL;
				simpanklien.depan = temp->next;
				free(temp);
			}
			else if (temp==simpanklien.belakang)
			{
				temp->prev->next = NULL;
				simpanklien.belakang = temp->prev;
				free(temp);
			}
			else
			{
				temp->prev->next = temp->next;
				temp->next->prev = temp->prev;
				free(temp);
			}
			simpanklien.jumlah--;
		}
	}
	nodeklien atNumber(int number)
	{
		nodeklien * temp;
		temp = simpanklien.depan;
		while (temp!=NULL && number!=temp->number)
		{
			temp = temp->next;
		}
		return *temp;
	}
	nodeklien atIndex(int index)
	{
		int i,j;
		nodeklien * temp;
		temp = simpanklien.depan;
		for (i=0;i<index;i++)
		{
			temp = temp->next;
		}
		return *temp;
	}

typedef struct argu_t
{
	int thread_id_number;
	char message[1000];
} argu_t;

void * sendToClient(void * ptr_argument)
{
	argu_t * ptr_argu_var = (argu_t *)ptr_argument;
	int thread_id_number = ptr_argu_var->thread_id_number;
	char pesan[200];
	strcpy(pesan,ptr_argu_var->message);
	write(atNumber(thread_id_number).client_socket_fd, pesan, strlen(pesan));
	return NULL;
}

void * receiveFromClient(void * ptr_argument)
{
	int * ptr_thread_id_number = (int *)ptr_argument;
	int thread_id_number = *ptr_thread_id_number - 1;
	while (1)
	{
		int banyak_byte;
		char pesantemp[100];
		char pesan[200];
		strcpy(pesan,"");
		while ((banyak_byte = read(atNumber(thread_id_number).client_socket_fd, pesantemp, 90))>0)
		{
			pesantemp[banyak_byte] = '\0';
			strcat(pesan,pesantemp);
		}
		char * tujuan = strstr(pesan,"@");
		if (tujuan!=NULL)
		{
			int panjang = strlen(tujuan);
			int i,j;
			for (i=0;i<panjang;i++)
			{
				tujuan[i] = tujuan[i+1];
			}
			for (i=0;i<simpanklien.jumlah;i++)
			{
				char * namaklien = atIndex(i).namaklien; 
				if (strcmp(tujuan,namaklien)==0)
				{
					pthread_t thread;
					argu_t argu_var;
					argu_var.thread_id_number = atIndex(i).number;
					strcpy(argu_var.message, pesan);
					pthread_create(&thread, NULL, sendToClient, (void *)&argu_var);	
				}
			}
		}
		else
		{
			int i,j;
			pthread_t thread[simpanklien.jumlah];
			argu_t argu_var[simpanklien.jumlah];
			for (i=0;i<simpanklien.jumlah;i++)
			{
				argu_var[i].thread_id_number = atIndex(i).number;
				strcpy(argu_var[i].message, pesan);
				pthread_create(&thread[i], NULL, sendToClient, (void *)&argu_var[i]);
			}
		}
	}
}

int main ()
{
	simpanklien.jumlah = 0;
	simpanklien.number_now = 1;
	simpanklien.depan = NULL;
	simpanklien.belakang = NULL;

	int socket_fd;
	int client_socket_fd;
	int ukuran_client;	

	struct sockaddr_in serveraddress;
	bzero(&serveraddress,sizeof(serveraddress));
	
	socket_fd = socket(AF_INET,SOCK_STREAM,IPPROTO_TCP);
	serveraddress.sin_family = AF_INET;
	serveraddress.sin_port = htons(1234);
	serveraddress.sin_addr.s_addr = htonl(INADDR_ANY);

	bind (socket_fd,(struct sockaddr*)&serveraddress,sizeof(serveraddress));
	while (1)
	{	
		struct sockaddr_in clientaddress;
		bzero(&clientaddress,sizeof(clientaddress));
		listen (socket_fd,20);
		client_socket_fd = accept(socket_fd,(struct sockaddr*)&clientaddress,(socklen_t*)&ukuran_client);
		char namaklien[500];
		char namaklientemp[200];
		int banyak_byte;
		strcpy(namaklien, "");
		while ((banyak_byte = read(client_socket_fd, namaklientemp, 150))>0)
		{
			namaklientemp[banyak_byte] = '\0';
			strcat(namaklien, namaklientemp);
		}
		pthread_t * ptr_thread_terima = (pthread_t *)malloc(sizeof(pthread_t));				
		push_back(clientaddress, namaklien, client_socket_fd, ptr_thread_terima);
		
		char online_users[1000];
		int i,j;
		strcpy(online_users,"");
		for (i=0;i<simpanklien.jumlah;i++)
		{
			strcat(online_users,"\n");
			strcat(online_users,atIndex(i).namaklien);
		}
		pthread_t thread[simpanklien.jumlah];
		argu_t argu_var[simpanklien.jumlah];
		for (i=0;i<simpanklien.jumlah;i++)
		{
			argu_var[i].thread_id_number = atIndex(i).number;
			strcpy(argu_var[i].message, online_users);
			pthread_create(&thread[i], NULL, sendToClient, (void *)&argu_var[i]);
		}		
		
		pthread_create(ptr_thread_terima, NULL, receiveFromClient, (void *)&simpanklien.number_now);
	}
	return 0;	
}
