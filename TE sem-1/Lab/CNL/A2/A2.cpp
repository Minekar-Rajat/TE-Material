#include<iostream>
#include<string.h>
using namespace std;

#define N strlen(g)

char t[28],cs[28],g[] = "1011";
int a,e,c;


void xor()
{
	for(c = 1;c<N ; c++)
	{
		cs[c] = ((cs[c] == g[c]) ? '0' : '1');
	}
}

void crc()
{
	for(e=0;e<N;e++)
	{
		cs[e]=t[e];
	}
	
	do
	{
		if(cs[0] == '1')
			xor();
		for(c = 0;c<N-1;c++)
		{
			cs[c] = cs[c+1];
		}
		cs[c] = t[e++];
		
	}while(e<= a+N-1);
	
}


int main()
{
	cout<<"\n\tEnter data	:	";
	cin>>t;
	cout<<"\n\t-------------------------------------";
	cout<<"\n\tGenerating polynomial	:	"<<g;
	a= strlen(t);
	for(e=a;e<a+N-1;e++)
	{
		t[e] = '0';
	}
	cout<<"\n\t-------------------------------------";
	cout<<"\n\tModified data is	:	"<<t;
	cout<<"\n\t-------------------------------------";
	crc();
	cout<<"\n\tChecksum is	:	"<<cs;
	for(e=a;e<a+N-1;e++)
	{
		t[e]=cs[e-a];	
	}	
	cout<<"\n\t-------------------------------------";
	cout<<"\n\tFinal codeward is	:	"<<t;
	cout<<"\n\t-------------------------------------";
	cout<<"\n\tTest error detection 0(yes)	1(no)	:	";
	cin>>e;
	if(e==0)
	{
		do{
			cout<<"\n\tEnter the position where error is to be inserted	:	";
			cin>>e;
		}while(e==0 || e>a+N-1);
		t[e-1] = (t[e-1] == '0') ? '1' : '0';
		cout<<"\n\t---------------------------------";
		cout<<"\n\tErroneous data	:	"<<t<<"\n";
	}
	crc();
	for(e=0;(e<N-1) && (cs[e] != '1');e++)
	{
		if(e<N-1)
		{
			cout<<"\n\tError Detected \n\n";
		}
		else
		{
			cout<<"\n\tNo Error Detection \n\n";
		}
	}
	cout<<"\n\t-------------------------------------\n\n";
	return 0;
	
		
}
