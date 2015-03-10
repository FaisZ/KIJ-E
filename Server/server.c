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
	struct nodeklien * next;
	struct nodeklien * prev;
} nodeklien;

typedef struct linkedlistklien
{
	int jumlah;
	nodeklien * depan;
	nodeklien * belakang;
	void push_back(struct sockaddr_in alamatklien, char namaklien[])
	{
		if (jumlah==0)
		{
			depan = (nodeklien *)malloc(sizeof(nodeklien));
			depan->alamatklien = alamatklien;
			strcpy(depan->namaklien, namaklien);
			depan->next = NULL;
			depan->prev = NULL;
			belakang = depan;
		}
		else
		{
			nodeklien * temp;
			temp = (nodeklien *)malloc(sizeof(nodeklien));
			temp->alamatklien = alamatklien;
			strcpy(temp->namaklien, namaklien);
			temp->next = NULL;
			temp->prev = belakang;
			belakang->next = temp;
			belakang = temp;
		}
		jumlah++;
	}
	void deleteAtIndex(int index)
	{
		if (jumlah==0) return;		
		else if (index<0 || index>=jumlah) return;
		else if (jumlah==1 && index==0)
		{
			free(depan);
			depan = NULL;
			belakang = NULL;
			jumlah = 0;
		}
		else
		{
			int i,j;
			nodeklien * temp;
			temp = depan;
			for (i=0;i<index;i++)
			{
				temp = temp->next;
			}
			if (temp==depan)
			{
				temp->next->prev = NULL;
				free(temp);
			}
			else if (temp==belakang)
			{
				temp->prev->next = NULL;
				free(temp);
			}
			else
			{
				temp->prev->next = temp->next;
				temp->next->prev = temp->prev;
				free(temp);
			}
			jumlah--;
		}
	}
	nodeklien atIndex(int index)
	{
		int i,j;
		nodeklien * temp;
		temp = depan;
		for (i=0;i<index;i++)
		{
			temp = temp->next;
		}
		return *temp;
	}
} linkedlistklien;

typedef struct nodethread
{
	pthread_t thethread;
	struct nodethread * next;
	struct nodethread * prev; 
} nodethread;

typedef struct linkedlistthread
{
	int jumlah;
	nodethread * depan;
	nodethread * belakang;
	void push_back(pthread_t thethread)
	{
		if (jumlah==0)
		{
			depan = (nodethread *)malloc(sizeof(nodethread));
			depan->thethread = thethread;
			depan->next = NULL;
			depan->prev = NULL;
			belakang = depan;
		}
		else
		{
			nodethread * temp;
			temp = (nodethread *)malloc(sizeof(nodethread));
			temp->thethread = thethread;
			temp->next = NULL;
			temp->prev = belakang;
			belakang->next = temp;
			belakang = temp;
		}
		jumlah++;
	}
	void deleteAtIndex(int index)
	{
		if (jumlah==0) return;		
		else if (index<0 || index>=jumlah) return;
		else if (jumlah==1 && index==0)
		{
			free(depan);
			depan = NULL;
			belakang = NULL;
			jumlah = 0;
		}
		else
		{
			int i,j;
			nodethread * temp;
			temp = depan;
			for (i=0;i<index;i++)
			{
				temp = temp->next;
			}
			if (temp==depan)
			{
				temp->next->prev = NULL;
				free(temp);
			}
			else if (temp==belakang)
			{
				temp->prev->next = NULL;
				free(temp);
			}
			else
			{
				temp->prev->next = temp->next;
				temp->next->prev = temp->prev;
				free(temp);
			}
			jumlah--;
		}
	}
	nodethread atIndex(int index)
	{
		int i,j;
		nodethread * temp;
		temp = depan;
		for (i=0;i<index;i++)
		{
			temp = temp->next;
		}
		return *temp;
	}
} linkedlistthread;

typedef struct buat_parsing_ke_thread_t
{
	
} buat_parsing_ke_thread_t;

void * berhubunganDenganKlien(void * parameter)
{
	
}

int main ()
{
	linkedlistklien simpanklien;
	simpanklien.jumlah = 0;
	simpanklien.depan = NULL;
	simpanklien.belakang = NULL;
	linkedlistthread simpanthread;
	simpanthread.jumlah = 0;
	simpanthread.depan = NULL;
	simpanthread.belakang = NULL;
	int socket_fd;
	int client_socket_fd;
	int ukuran_client;
	pthread_t thread_now;	

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
		pthread_create();
	}	
}
