import socket
i="1"
while i=="1" or i=="2":
	i = input("Enter 1 for IP to domain name or 2 for Domain Name to IP: ")
	if i=="1":
		j = input("Enter IP Address: ")
		domain = socket.gethostbyaddr(j)[0]
		print(domain)
	if i=="2":
		k = input("Enter domain name: ")
		ip = socket.gethostbyname(k)
		print(ip)
