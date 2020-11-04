#include<sys/socket.h>
#include<arpa/inet.h>
#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>
#include<sys/types.h>
#include<string.h>
#include<stdlib.h>
#define maxlen 70000
#define mlen 100000
int main()
{
  char fileName[100];
  char filebuffer[2000],caufile[maxlen];
  char *vfilep;
  int aufile[700000],vfile[mlen];
  int sd,connfd,len;

for(int i=0;i<=100;i++){
	fileName[i]='\0';
}
  struct sockaddr_in servaddr,cliaddr;

//--------------------creation of socket---------------------
  sd = socket(AF_INET, SOCK_DGRAM, 0);

  if(sd==-1)
    {
      printf(" socket not created in server\n");
      exit(0);
    }
  else
    {
      printf("socket created in  server\n");
    }

  bzero(&servaddr, sizeof(servaddr));

// add the parameters
  servaddr.sin_family = AF_INET;
  servaddr.sin_addr.s_addr = INADDR_ANY;
  servaddr.sin_port = htons(8000);
  memset(&(servaddr.sin_zero),'\0',8);



  //--------------------binding the socket-----------------------
  if ( bind(sd, (struct sockaddr *)&servaddr, sizeof(servaddr)) != 0 )
    printf("Not binded\n");
  else
    printf("Binded\n");

  len=sizeof(cliaddr);

  int choice =1;
  while(1)
  {
  	char num;


// receiving choice from client
  	recvfrom(sd,&num,sizeof(num),0,(struct sockaddr *)&cliaddr, &len);

// converting the character to int
	choice = num;


	switch(choice)
	{
		case 1:
    // Text file
			recvfrom(sd,fileName,1024,0,(struct sockaddr *)&cliaddr, &len);
  			printf("NAME OF TEXT FILE RECEIVED : %s\n",fileName);
			FILE *fp;
  	 		printf("Contents in the received text file : \n");
        // filebuffer saves the contents
  	 		recvfrom(sd,filebuffer,1024,0,(struct sockaddr *)&cliaddr, &len);
  	 		printf("%s\n",filebuffer);
  			int fsize=strlen(filebuffer);
			fp=fopen(fileName,"w");
      // writing to the file
  			if(fp)
  			{
 				fwrite(filebuffer, fsize, 1, fp);
  				printf("File received successfully.\n");
  			}
  			else
  			{
	  			printf("Cannot create to output file.\n");
  			}
  			memset(fileName, '\0', sizeof(fileName));
  			fclose(fp);
  			break;
  		case 2:
      // Audio File
  			recvfrom(sd,fileName,1024,0,(struct sockaddr *)&cliaddr, &len);
   			printf("NAME OF AUDIO FILE RECEIVED : %s\n",fileName);
   			FILE *afp;
   			int numbytes;

     			afp=fopen(fileName,"w");
     			size_t  afsize;
          // receiving bytes
     			afsize=recvfrom(sd,aufile,700000,0,(struct sockaddr *)&cliaddr, &len);
     			if(afp)
     			{
     				fwrite(aufile, afsize, 1, afp);
     				printf("File received successfully.\n");
     			}
     			else
     			{
   	  			printf("Cannot open output file.\n");
     			}
     			memset(fileName, '\0', sizeof(fileName));
     			fclose(afp);
     			break;

     		case 3:
        // Video File
     			recvfrom(sd,fileName,1024,0,(struct sockaddr *)&cliaddr, &len);
       			printf("VIDEO FILE NAME RECEIVED : %s\n",fileName);
       			FILE *vfp;
       			vfp=fopen(fileName,"w");
       			size_t  vfsize;
            // receiving bytes
       			vfsize=recvfrom(sd,vfile,1000000,0,(struct sockaddr *)&cliaddr, &len);

       			if(vfp)
       			{
           			fwrite(vfile, vfsize, 1, vfp);
           			printf("File received successfully.\n");
           		}
           		else
           		{
         	  		printf("Cannot open output file.\n");
           		}
     			fclose(vfp);
     			break;

     		case 4:
        // Exit case
  			close(sd);
  			break;

	}
  }
  return(0);
}
