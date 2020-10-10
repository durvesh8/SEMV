/*
filename server_ipaddress portno

*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>


void error(const char *msg) {
    perror(msg);
    exit(1);
}

int main(int argc, char const *argv[]) {
    int sockfd,portno,n;
    struct sockaddr_in serv_addr;
    struct hostent *server;
    printf("d1\n");
    char buffer[255];
    if(argc<3){
        fprintf(stderr, "usage %s hostname port\n",argv[0]);
        exit(1);
    }
    portno = atoi(argv[2]);
    sockfd = socket(AF_INET,SOCK_STREAM,0);
    if(sockfd < 0)
    error("Error opening Socket");
    printf("d2\n");
    server = gethostbyname(argv[1]);
    if(server == NULL)
    fprintf(stderr,"Error, no such host");
    printf("d3\n");
    bzero((char *)&serv_addr,sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    bcopy((char*)server->h_addr,(char *)&serv_addr.sin_addr.s_addr,server->h_length);
    serv_addr.sin_port = htons(portno);   // host to network short
    printf("d4\n");
    if(connect(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr))<0){
        error("Connection failed");
    }
    printf("it's here?");
    if(portno==9890){
        while(1){
            bzero(buffer,255);
            fgets(buffer,255,stdin);
            int j = strncmp("bye",buffer,3);
            if (j==0)
            break;
            n = write(sockfd,buffer,strlen(buffer));
            if(n<0)
            error("Error on writing");
            bzero(buffer,255);
            n = read(sockfd,buffer,255);
            if(n<0)
            error("Error on reading");
            printf("Server says: %s\n",buffer);
            int i = strncmp("bye",buffer,3);
            if (i==0)
            break;
        }
    }
    if(portno==9891){

    }
    if(portno==9892){
        while(1){
            printf("It's here alright\n");
            int num1,num2,choice,answer;
            bzero(buffer,255);
            n = read(sockfd,buffer,255);
            if(n<0)
                error("Error reading");
            printf("Server- %s",buffer);
            scanf("%d", &num1);
            n = write(sockfd,&num1,sizeof(int));
            if (n<0) {
                error("Error writing\n");
            }
            bzero(buffer,255);
            n = read(sockfd,buffer,255);
            if(n<0)
            error("Error reading");
            printf("Server- %s",buffer);
            scanf("%d", &num2);
            n = write(sockfd,&num2,sizeof(int));
            if (n<0) {
                error("Error writing\n");
            }
            bzero(buffer,255);
            n = read(sockfd,buffer,255);
            if(n<0)
            error("Error reading");
            printf("Server- %s\n",buffer);
            scanf("%d", &choice);

            n = write(sockfd,&choice,sizeof(int));
            if (n<0) {
                error("Error writing\n");
            }
            if(choice==5){
                goto E;
                break;
            }
            printf("Answer: ");
            n = read(sockfd,&answer,sizeof(int));
            printf("%d\n",answer );
        }
    }

    E:  close(sockfd);
    return 0;
}
