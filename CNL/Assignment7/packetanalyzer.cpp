#include <iostream>
#include<fstream>
#include <iomanip>
#include<string>
using namespace std;

int main() {
	cout << "***** PACKET ANALYZER *****" << endl; 
	string value, sr_no,time,source,destination,info,protocol,len;
	int count=-1,i=0;

	int choice=0;
	while(choice!=5)	
	{
		ifstream file("data.csv");
		count=-1;
		i=0;
	cout<<"\nEnter which protocol packets you want to see"<<endl;
	cout<<"1.IP\n2.UDP\n3.TCP\n4.Ethernet\n5.Exit!!!\nChoice:";
	cin>>choice;
	string protocolChoice; 
	string[] protocolChoices=["ICMPv6","UDP","TCP","ARP"];
	if (choice>5 || choice<1){
		protocolChoice = "ARP";
	}
	else if(choice==5){
	break;

	}
	else{
		protocolChoice = protocolChoices[choice-1];
	}
	while(file.good()) 
	{
		getline(file,sr_no,','); 
		getline(file,time,',');
		getline(file,source,',');
		getline(file,destination,',');
		getline(file,protocol,',');
		getline(file,len,',');
		getline(file,info,'\n');

		protocol=string(protocol,1,protocol.length()-2);

		if(protocol=="Protocol"||protocol==protocolChoice)
		{
			cout <<setw(4)<<left<<i++;
			cout <<setw(12)<<left<< string( time, 1, time.length()-2 );
			cout << setw(30)<<left<<string( source, 1, source.length()-2 );
			cout << setw(30)<<left<<string( destination, 1, destination.length()-2 );
			cout <<setw(8)<<left<<protocol<<"  ";
			cout <<setw(8)<<left<< string( len, 1, len.length()-2 );
			cout << string( info, 1, info.length()-2 )<<"\n";
			count++;
		}
	}
	file.close();
	cout<<"\nTotal Packet Count: "<<count<<endl;
	}while(choice!=5);
	return 0;
}

