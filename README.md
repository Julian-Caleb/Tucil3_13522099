# Tugas Kecil 3
## IF2211 Strategi Algoritma
## Word Ladder
dibuat oleh Julian Caleb Simandjuntak / 13522099

## Deskripsi Singkat
Tugas Kecil 3 IF2211 Strategi Algoritma ini adalah sebuah program berbahasa Java yang mensimulasikan permainan Word Ladder yang mencari rute dari sebuah kata ke kata lainnya dengan cara setiap langkahnya mengubah 1 buah kata dengan syarat setiap kata yang ada adalah kata berbahasa Inggris yang terdapat dalam kamus. Program ini berbasis GUI dan menggunakan sebuah kamus lokal berekstensi txt. Program dapat menggunakan algoritma Uniform Cost Search (UCS), Greedy Best First Search (GBFS), dan A*. Pembangkitan cost UCS menggunakan g(n) yaitu penjumlahan cost sementara perbedaan huruf pada satu langkah (yaitu 1). Pembangkitan cost GBFS menggunakan h(n) yaitu perbedaan jumlah huruf kata yang dibangkitkan dengan kata tujuan. Pembangkitan cost A* menggunakan f(n) yaitu g(n) + h(n).

## How to run
1. Compile
```javac -d bin src/Main.java src/GUI.java src/EnglishDictionary.java src/WordLadder.java src/Node.java```

2.  Run Program
```java -cp bin Main```