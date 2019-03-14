Ex3
public class Ex3 {
public static void main (string args [])
{

Scanner sc = new Scanner(System.in); 
int d,m,a, nextd;
System.out.println("Entre l'année");
a= sc.nextInt();
boolean isBissext = (((a%100==0) || (a%4==0)) || (a%==00!=0));

do { 
System.out.println("Entrez le mois 1...12: ");
m = sc.nextInt();
} while ((m<1) || (m>2));

int maxJour;
if ((m==1)||(m==3)||(m==7)||(m==8)||(m==10)||(m==12)) { maxJour = 31;}
else if ((m==2)&& isBissext){
 maxJour = 29; 
else maxJour=28;
}
else maxJour=30;

do { 
System.out.print("Entrez le jour 1..." + maxJour);
d= sc.nextInt();
} while ((d<1) ||(d>maxJour));

// Calcul de la date du lendemain

if (m==12 && d == maxJour) {
a++;
}

if (d== maxJour) {
m = (m%12)+1;
}
v 
d = (d%maxJour)+1;

System.out.println("le jour du lendemain est le :" +d+"/"+m+"/"+a);

System.out.print("

}
}
