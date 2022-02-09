#include<stdio.h>
#include<string.h>
#define N strlen(g)
char t[28],cs[28],g[]="1011";
int a,e,c;
void xor(){
 for(c = 1;c < N; c++)
 cs[c] = (( cs[c] == g[c])?'0':'1');
}
void crc(){
 for(e=0;e<N;e++)
 cs[e]=t[e];
 do{
 if(cs[0]=='1')
 xor();
 for(c=0;c<N-1;c++)
 cs[c]=cs[c+1];
 cs[c]=t[e++];
 }while(e<=a+N-1);
}
int main()
{
 printf("\n\tEnter data	:	");
 scanf("%s",t);
 printf("\n\t----------------------------------------");
 printf("\n\tGeneratng polynomial : %s",g);
 a=strlen(t);
 for(e=a;e<a+N-1;e++)
 t[e]='0';
 printf("\n\t----------------------------------------");
 printf("\n\tModified data is : %s",t);
 printf("\n\t----------------------------------------");
 crc();
 printf("\n\tChecksum is : %s",cs);
 for(e=a;e<a+N-1;e++)
 t[e]=cs[e-a];
 printf("\n\t----------------------------------------");
 printf("\n\tFinal codeword is : %s",t);
 printf("\n\t----------------------------------------");
 printf("\n\tTest error detection 0(yes) 1(no)? : ");
 scanf("%d",&e);
 if(e==0)
 {
 do{
 printf("\n\tEnter the position where error is to be inserted : ");
 scanf("%d",&e);
 }while(e==0 || e>a+N-1);
 t[e-1]=(t[e-1]=='0')?'1':'0';
 printf("\n\t----------------------------------------");
 printf("\n\tErroneous data : %s\n",t);
 }
 crc();
 for(e=0;(e<N-1) && (cs[e]!='1');e++);
 if(e<N-1)
 printf("\n\tError detected\n\n");
 else
 printf("\n\tNo error detected\n\n");
 printf("\n\t----------------------------------------\n");
 return 0;
}
