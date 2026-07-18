/*
-Esquema principal

#include <iostream>
-bibliotecas

using namespace std;

-Constantes y tipos(enum array y string)

-funciones y procedimientos

int main(){
    
    return 0;
}   */

#include <iostream>
#include <string>           // biblotecas de arrays y string
#include <array>
using namespace std;

enum colore{
    rojo,
    azul,
    amarillo,               //tipo enum, rojo =0 azul 1...
    blanco
};

const int TAM_A=13;            //constante tamaño array
typedef array<int, TAM_A> cus;     //tipo array de 13 enteros 0 a 12

const int NFILAS=6;
const int NCOLUMNAS=8;              //matriz 2d
typedef array<int,NCOLUMNAS> TFila;
typedef array<TFila,NFILAS> TMatriz;

struct Node{
    int elemento;   //Nodo, para crear estructuras enlazadas ejemplo con int
    Node* next;
};

struct tabla{
    int x=0;          //tipo registro, tipo tabla, x numeros rellenos y m el array
    cus m={{}};         //def array vacia, si nada, indet y si numeros dentro, eso.
};

const int TAM=2;            //constantes

void culon(char& x){        //procedimiento
    do{                                     //bucle do while con cin.get, cin pero lee los espacios y solo para char
        cin.get(x);
    }while(x!='x' && x<='b');
}

bool verda (int x){         // funcion tipo bool(puede ser de cualquier tipo)
    bool y;
    switch(x){              //if tipo switch
        case 2: y=true;
                break;
        default:
                break;
    }
    return y;                  // siempre se devuelve algo
}

int main(){
    char z, w;
    int y, i;
    double x;               //delcaracion variables  bool char int float double colore(creada) unsigned(no) string array y registros
    bool culo=false;
    cin >> x >> z;                                               //leer
    cout << "hola \n" << TAM << endl;       //mostrar, separaando una linea
    if(culo){
        x=x+TAM;                                    //if anidados y else con suma;
    }else if(TAM==x){
        culon(z);                                   //procedimiento
    }
    else{
        culo=verda(x);                          //funcion
    }
    sizeof(bool);                    //tabaño en bits de la variable tipo bool
    while(!culo){
        culo=(!culo)? verda(x) : false;                                 //puede ser un cout
    }

    //esto iría arriba con las variables, pero lo separo para que se vea mejor
    string cad, cad2, cad3, cad1;           //declaracion de cadena
    cad="hola";
    cad[0]='H';                 //cambiar un caracter de la cadena
    cad= cad + " mundo";        //concatenar
    getline(cin,cad2);           //leer una linea, cin solo 1 palabra, si otra , y un caracter entre '', lee hasta ahi (cin,cad2,'.')
    y=cad.size();                 //longitud de la cadena, se pueden comparar por longitud o alfabeticamente con ==, !=, <, >, <=, >= (sirve en el array)
    w=cad[4];                   //leer un caracter de la cadena posicion de 0 a cad.size()-1 (sirve en el array)
    cad3=cad.substr(2,3);       //subcadena desde la posicion 2, 3 caracteres
    i=cad.compare(cad2);       //comparar cadenas, devuelve 0 si son iguales, <0 si la primera es menor, >0 si la primera es mayor
    cad.swap(cad2);            //intercambiar cadenas
    for(char c: cad){           
        cout << c << endl;      //recorrer cadena bucle for each q muestra cada caracter y la inversa (sirvw en el array)
        cad1=c+cad1;

        tabla t;                  //declaracion tabla
        for(int i=0; i<TAM_A; i++){
           cin >> t.m[i];           //leer array
           t.x=t.x+1;          //contador en el array por la tabla.
        }
    }
    TMatriz m;                  //declaracion matriz
    m[0][0]=1;                  //rellenar matriz en 0,0
    m={{{{3,2,5}},{{1,2,3}}}};  //rellenar matriz collumna a columna

    //Uso de punteros y nodos
    int a= 5;
    int * p= &a;                //declaracion puntero tipo* nombre= &variable; & es dir de memoria de la variable
    cout << *p << endl;         //mostrar el valor de la variable a traves del puntero, * es el valor de la variable
    Node* n1 = (struct Node *)malloc(sizeof(struct Node)); //declaracion nodo
    n1->elemento=5;             //rellenar nodo
    n1->next=NULL;              //siguiente nodo

    return 0;
}


//throw -> excepcion tryCatch
//parametros en procedimintos por valor no se editan, sin &. Por referenciav si se editan, con &. Tipos estructurados por referencia y const o no menos for each.