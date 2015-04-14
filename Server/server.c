#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<pthread.h>
#include<arpa/inet.h>

typedef struct nodeklien
{
	struct sockaddr_in alamatklien;
	char namaklien[500];
	int client_socket_fd;
	//pthread_t * ptr_thread_terima;
	struct nodeklien * next;
	struct nodeklien * prev;
} nodeklien;

typedef struct linkedlistklien_t
{
	int jumlah;
	nodeklien * depan;
	nodeklien * belakang;	
} linkedlistklien_t;

linkedlistklien_t simpanklien;

void push_back(struct sockaddr_in alamatklien, 
		char namaklien[], 
		int client_socket_fd  
		//pthread_t * ptr_thread_terima
		)
{
	if (simpanklien.jumlah==0)
	{
		simpanklien.depan = (nodeklien *)malloc(sizeof(nodeklien));
		simpanklien.depan->alamatklien = alamatklien;
		strcpy(simpanklien.depan->namaklien, namaklien);
		simpanklien.depan->client_socket_fd = client_socket_fd;
		//simpanklien.depan->ptr_thread_terima = ptr_thread_terima;
		simpanklien.depan->next = NULL;
		simpanklien.depan->prev = NULL;
		simpanklien.belakang = simpanklien.depan;
	}
	else
	{
		nodeklien * temp;
		temp = (nodeklien *)malloc(sizeof(nodeklien));
		temp->alamatklien = alamatklien;
		strcpy(temp->namaklien, namaklien);
		temp->client_socket_fd = client_socket_fd;
		//temp->ptr_thread_terima = ptr_thread_terima;
		temp->next = NULL;
		temp->prev = simpanklien.belakang;
		simpanklien.belakang->next = temp;
		simpanklien.belakang = temp;
	}
	simpanklien.jumlah++;
}
void deleteNodeklienByName(char nama[])
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
		while (temp!=NULL && strcmp(nama,temp->namaklien)!=0)
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
nodeklien getNodeklienByName(char nama[])
{
	nodeklien * temp;
	temp = simpanklien.depan;
	while (temp!=NULL && strcmp(nama,temp->namaklien)!=0)
	{
		temp = temp->next;
	}
	return *temp;
}
nodeklien getNodeklienByIndex(int index)
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
	char nama[500];
	char message[1000];
} argu_t;
argu_t argu_var[300];
int index_argu_var = 0;
pthread_t thread[300];
int index_thread = 0;

void * sendToClient(void * ptr_argument)
{
	puts ("fungsi sendToClient berhasil dijalankan");
	argu_t * ptr_argu_var = (argu_t *)ptr_argument;
	char namatujuan[500];
	strcpy(namatujuan, ptr_argu_var->nama);
	char pesan[200];
	strcpy(pesan,ptr_argu_var->message);
	strcat(pesan, "\r");	

	write(getNodeklienByName(namatujuan).client_socket_fd, pesan, strlen(pesan));
	return NULL;
}

void * receiveFromClient(void * ptr_argument)
{
	puts ("fungsi receiveFromClient berhasil dijalankan");
	argu_t * ptr_argu_var = (argu_t *)ptr_argument;
	char namasumber[500];
	strcpy(namasumber,ptr_argu_var->nama);
	while (1)
	{
		int banyak_byte;
		char karakterpesan;
		char pesan[200];
		int iter = 0;
		int isBroadcast = 1;
		int posisiAt = -1;
		strcpy(pesan,"");
		while ((banyak_byte = read(getNodeklienByName(namasumber).client_socket_fd, &karakterpesan, 1))>0)
		{
			if (karakterpesan=='@') posisiAt = iter; 			
			if (karakterpesan=='\r') break;			
			pesan[iter] = karakterpesan;
			iter++;
		}
		pesan[iter] = '\0';
		char * ptr_cek = strstr(pesan,"@");
		if (ptr_cek!=NULL)
		{
			//puts ("error 1");
			char tujuan[200];
			//puts ("error 2");
			strcpy(tujuan,ptr_cek);
			//puts ("error 3");			
			int panjang = strlen(tujuan);
			int i,j;
			for (i=0;i<panjang;i++)
			{
				tujuan[i] = tujuan[i+1];
			}
			pesan[posisiAt] = '\0';
			for (i=0;i<simpanklien.jumlah;i++)
			{
				nodeklien nk = getNodeklienByIndex(i); 
				if (strcmp(tujuan,nk.namaklien)==0)
				{
					//pthread_t thread;
					//argu_t argu_var;
					strcpy(argu_var[index_argu_var].nama, nk.namaklien);
					strcpy(argu_var[index_argu_var].message, pesan);
					pthread_create(&thread[index_thread], NULL, sendToClient, (void *)&argu_var[index_argu_var]);
					index_argu_var++;
					index_thread++;	
				}
			}
		}
		else
		{
			int i,j;
			//pthread_t thread[simpanklien.jumlah];
			//argu_t argu_var[simpanklien.jumlah];
			for (i=0;i<simpanklien.jumlah;i++)
			{
				nodeklien nk = getNodeklienByIndex(i); 
				strcpy(argu_var[index_argu_var].nama, nk.namaklien);
				strcpy(argu_var[index_argu_var].message, pesan);
				pthread_create(&thread[index_thread], NULL, sendToClient, (void *)&argu_var[index_argu_var]);
				index_argu_var++;
				index_thread++;
			}
		}
	}
}

int main ()
{
	simpanklien.jumlah = 0;
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
		//puts ("ada yang masuk");
		char namaklien[500];
		char karakternamaklien;
		int iter = 0;
		int banyak_byte;
		strcpy(namaklien, "");
		//puts ("test lagi");
		while ((banyak_byte = read(client_socket_fd, &karakternamaklien, 1))>0)
		{
			if (karakternamaklien=='\r') break;
			namaklien[iter] = karakternamaklien;
			iter++;
		}
		namaklien[iter] = '\0';
		//puts ("tes lagi 2");
		//pthread_t * ptr_thread_terima = (pthread_t *)malloc(sizeof(pthread_t));				
		push_back(clientaddress, namaklien, client_socket_fd);
		
		char online_users[1000];
		int i,j;
		strcpy(online_users,"");
		for (i=0;i<simpanklien.jumlah;i++)
		{
			strcat(online_users,"\n");
			nodeklien nk = getNodeklienByIndex(i);
			strcat(online_users,nk.namaklien);
		}
		//pthread_t thread[simpanklien.jumlah];
		//argu_t argu_var[simpanklien.jumlah];
		for (i=0;i<simpanklien.jumlah;i++)
		{
			nodeklien nk = getNodeklienByIndex(i);
			strcpy(argu_var[index_argu_var].nama, nk.namaklien);
			strcpy(argu_var[index_argu_var].message, online_users);
			//argu_var[i].thread_id_number = getNodeklienByIndex(i).number;
			pthread_create(&thread[index_thread], NULL, sendToClient, (void *)&argu_var[index_argu_var]);
			printf ("halo halo\n");
			index_argu_var++;
			index_thread++;
		}
		strcpy(argu_var[index_argu_var].nama, namaklien);	
		pthread_create(&thread[index_thread], NULL, receiveFromClient, (void *)&argu_var[index_argu_var]);
		//puts ("berhasil lewat sampe akhir looping");
	}
	return 0;	
}
