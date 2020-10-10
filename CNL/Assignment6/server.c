#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>



void error(const char *msg) {
    perror(msg);
    exit(1);
}


int main(int argc, char const *argv[]) {
    if(argc<2){
        fprintf(stderr,"Port number not provided.\n");
        exit(1);
    }
    int sockfd, newsockfd, portno, n;
    char buffer[255]; // The data to be sent to the server and received from it

    struct sockaddr_in serv_addr, cli_addr;
    socklen_t clilen;

    sockfd = socket(AF_INET, SOCK_STREAM, 0);       //SOCK_STREAM for TCP
    if(sockfd<0){
        error("Error opening the server socket");
    }

    bzero((char *)&serv_addr,sizeof(serv_addr));
    portno = atoi(argv[1]);

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(portno);     // Host to network short

    if(bind(sockfd,(struct sockaddr *) &serv_addr,sizeof(serv_addr))<0){
        error("Binding Failed.");
    }

    listen(sockfd, 5);
    clilen = sizeof(cli_addr);

    newsockfd = accept(sockfd,(struct sockaddr *)&cli_addr,&clilen);

    if(newsockfd < 0)
    error("Error accepting");
    if(portno==9890){
        while(1){
            bzero(buffer,255);
            n = read(newsockfd,buffer,255);
            if(n<0)
            error("Error on reading");
            printf("Client: %s\n",buffer);
            bzero(buffer,255);
            fgets(buffer,255,stdin);  // Reading from the server terminal
            n = write(newsockfd,buffer,strlen(buffer));
            if(n<0)
            error("Error on writing\n");
            int i = strncmp("bye",buffer,3);
            if (i==0)
            break;
        }
    }
    if(portno=9891){

    }
    if(portno=9892){
        printf("It's here alright\n");
        int num1,num2,answer,choice;
        char choices[5][15]={"Addition","Subtraction","Multiplication","Division","Exit"};

S:        n = write(newsockfd,"Enter number 1: ",strlen("Enter number 1: "));
        if(n<0)
            error("Error on writing\n");
        read(newsockfd,&num1,sizeof(int));
        printf("Client number 1 is: %d\n",num1);
        n = write(newsockfd,"Enter number 2: ",strlen("Enter number 2: "));
        if(n<0)
            error("Error on writing\n");
        read(newsockfd,&num2,sizeof(int));
        printf("Client number 2 is: %d\n",num2);

        n = write(newsockfd,"1. Addition\n2. Subtraction\n3. Multiplication\n4. Division\n5. Exit\n",
                    strlen("1. Addition\n2. Subtraction\n3. Multiplication\n4. Division\5. Exit\n"));
        if(n<0)
            error("Error on writing\n");
        read(newsockfd,&choice,sizeof(int));
        printf("Client operation is: %s\n",choices[choice-1]);
        switch (choice) {
            case 1:
                answer = num1+num2;
                break;
            case 2:
                answer = num1-num2;
                break;
            case 3:
                answer = num1*num2;
                break;
            case 4:
                answer = num1/num2;
                break;
            case 5:
                goto Q;
                break;

        }
        write(newsockfd,&answer,sizeof(int));
        if(choice!=5){
            goto S;
        }


    }
Q:    close(newsockfd);
    close(sockfd);
    return 0;
}
