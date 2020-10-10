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
#include <ctype.h>


void error(const char *msg) {
    perror(msg);
    exit(1);
}

int main(int argc, char const *argv[]) {
    int sockfd,portno,n;
    struct sockaddr_in serv_addr;
    struct hostent *server;
    char buffer[255];
    if(argc<3){
        fprintf(stderr, "usage %s hostname port\nport 9890 for chat\nport 9891 for file transfer\nport 9892 for calculator\n",argv[0]);
        exit(1);
    }
    portno = atoi(argv[2]);     // String to integer
    sockfd = socket(AF_INET,SOCK_STREAM,0);
    if(sockfd < 0)
    error("Error opening Socket");
    server = gethostbyname(argv[1]);
    if(server == NULL)
    fprintf(stderr,"Error, no such host");
    bzero((char *)&serv_addr,sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    bcopy((char*)server->h_addr,(char *)&serv_addr.sin_addr.s_addr,server->h_length);
    serv_addr.sin_port = htons(portno);   // host to network short
    if(connect(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr))<0){      // Connecting to Server
        error("Connection failed");
    }
    if(portno==9890){           // Chat App
        while(1){
            bzero(buffer,255);
            fgets(buffer,255,stdin);    // Reading from client terminal
            int j = strncmp("bye",buffer,3);
            if (j==0)
            break;
            n = write(sockfd,buffer,strlen(buffer));        // Sending to server
            if(n<0)
            error("Error on writing");
            bzero(buffer,255);
            n = read(sockfd,buffer,255);                    // Reading from server
            if(n<0)
            error("Error on reading");
            printf("Server says: %s\n",buffer);             // Printing server's sent message
            int i = strncmp("bye",buffer,3);
            if (i==0)
            break;
        }
    }
    if(portno==9891){       // File Transfer
        FILE *f;
        int words = 0;
        char c;
        f = fopen("textfile.txt","r");      // Opening an already existing file to send its data to server
        while((c= getc(f))!= EOF){          // Counting the number of words
            fscanf(f,"%s",buffer);
            if(isspace(c)||c=='\t')
            words++;
        }
        write(sockfd,&words,sizeof(int));       // Sending the number of words
        rewind(f);          // Setting the file pointer at initial word

        char ch;
        while(ch!=EOF){
            fscanf(f,"%s",buffer);
            write(sockfd,buffer,255);       // Sending words one by one
            ch = fgetc(f);
        }
        printf("The file has been sent.\n");

    }
    if(portno==9892){
        while(1){
            int num1,num2,choice,answer;
            bzero(buffer,255);
            n = read(sockfd,buffer,255);            // Reading first request from server
            if(n<0)
                error("Error reading");
            printf("Server- %s",buffer);            // printing first request
            scanf("%d", &num1);                     // getting num1 from client terminal
            n = write(sockfd,&num1,sizeof(int));        // sending num1 to server
            if (n<0) {
                error("Error writing\n");
            }
            bzero(buffer,255);
            n = read(sockfd,buffer,255);            // Reading first request from server
            if(n<0)
            error("Error reading");
            printf("Server- %s",buffer);            // printing first request
            scanf("%d", &num2);                         // getting num1 from client terminal
            n = write(sockfd,&num2,sizeof(int));        // sending num2 to server
            if (n<0) {
                error("Error writing\n");
            }
            bzero(buffer,255);
            n = read(sockfd,buffer,255);
            if(n<0)
            error("Error reading");
            printf("Server- %s\n",buffer);
            scanf("%d", &choice);               // Reading operation choice from client

            n = write(sockfd,&choice,sizeof(int));
            if (n<0) {
                error("Error writing\n");
            }
            if(choice==5){      // Exit case
                goto E;
                break;
            }
            printf("Answer: ");
            n = read(sockfd,&answer,sizeof(int));       // Getting the answer.
            printf("%d\n",answer );             // printing the answer
        }
    }

    E:  close(sockfd);
    return 0;
}
