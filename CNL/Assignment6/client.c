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

int int main(int argc, char const *argv[]) {
    int sockfd,portno,n;
    struct sockaddr_in serv_addr;
    struct hostent *server;

    char buffer[255];
    if(argc<3){
        fprintf(stderr, "usage %s hostname port\n", );
        exit(0);
    }
    portno = atoi(argv[2]);
    sockfd = socket(AF_INET,SOCK_STREAM,0);
    
    return 0;
}
