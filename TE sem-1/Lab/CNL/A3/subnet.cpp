#include <iostream>
#include<bits/stdc++.h>
using namespace std;
int main()
{
 int a[4],netbits,subnets,bitsub,netmask[4]={0},offset,subnetmask[4]={0};
 int subcal,fixedoct,inp1[4],inp2[4];
 cout<<"\n\n\tEnter the IP address with spaces:: ";
 for(int i=0;i<4;i++)
 {
 cin>>a[i];
 }
 cout<<"\n\n\tEnter the number of network-id bits:: ";
 cin>>netbits;
 if(netbits<8 || netbits>32)
 {
 cout<<"\n\n\t Invalid "<<endl;
 }
 else
 {
 cout<<"\n\n\tEnter the no. of subnets to be formed: ";
 cin>>subnets;
 bitsub = ceil(log2(subnets));
 int subnetids[int(pow(2,bitsub))];
 offset = netbits;
 int i=0;
 while(offset>=8)
 {
 netmask[i]= 255;
 subnetmask[i] = 255;
 i++;
 offset = offset - 8;
 }
 fixedoct =i;
 subcal = bitsub+offset;
 while(offset!=0)
 {
 netmask[i]+=pow(2,8-offset);
 offset--;
 }
 subnetmask[i]=netmask[i];
 offset = subcal;
 while(bitsub!=0)
 {
 subnetmask[i]+=pow(2,8-offset);
 bitsub--;
 offset--;
 }
 cout<<"\n\n\tThe Network Mask is:: "<<endl;
 for(int i=0;i<4;i++)
 {
 cout<<netmask[i];
 if(i!=3)
 cout<<".";
 }
 cout<<"\n\n\tThe Sub-Net Mask is:: "<<endl;
 for(int i=0;i<4;i++)
 {
 cout<<subnetmask[i];
 if(i!=3)
 cout<<".";
 }
 bitsub = ceil(log2(subnets));
 for(int i=0;i<pow(2,bitsub);i++)
 {
 int p = i;
 subnetids[i] = p<<(8-subcal);

 }
 cout<<"\n\n\tThe Subnet Id's are:: "<<endl;
 for(int i=0;i<pow(2,bitsub);i++)
 {
 for(int j=0;j<4;j++)
 {
 if(j!=fixedoct)
 cout<<a[j];
 else
 cout<<a[j]+subnetids[i];
 if(j!=3)
 cout<<".";
 }
 cout<<"\n";
 }
 cout<<"\n\n\tEnter the 2 subnet-id's(with spaces)"<<endl;
 for(int i=0;i<4;i++)
 {
 cin>>inp1[i];
 }
 for(int i=0;i<4;i++)
 {
 cin>>inp2[i];
 }
 int n=pow(2,bitsub);
 for(i=0;i<n-1;i++)
 {
 if(inp1[fixedoct]>subnetids[i] && inp1[fixedoct]<subnetids[i+1] )
 {
 if(inp2[fixedoct]>subnetids[i] && inp2[fixedoct]<subnetids[i+1] )
 {
 cout<<"\n\n\tThey are in the same subnet-id::";
 for(int j=0;j<4;j++)
 {
 if(j!=fixedoct)
 cout<<a[j];
 else
 cout<<subnetids[i];
 if(j!=3)
 cout<<".";
 }
 cout<<"\n";
 break;
 }
 }
 }
 if(i>=n-1)
 cout<<"\n\n\tThey are not in the same subnet-id\n";
 }
return 0;
}
