/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlist;

import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author labitson
 */
public class ListaEnlazada<T> implements IList<T>, Iterable<T> {

    protected NodoSimple<T> inicio;
    protected int nElementos;
    private Scanner sc;

    public ListaEnlazada() {
        inicio = null;
        nElementos = 0;
    }

    private class NodoSimple<T> {

        private T dato;
        private NodoSimple<T> sig;

        public NodoSimple(T dato) {
            this.dato = dato;
        }

    }

    private class ListIterator<T> implements Iterator<T> {

        private NodoSimple<T> nodoActual;

        public ListIterator(NodoSimple<T> inicio) {
            nodoActual = inicio;
        }

        @Override
        public boolean hasNext() {
            return nodoActual != null;
        }

        @Override
        public T next() {
            T dato = nodoActual.dato;
            nodoActual = nodoActual.sig;

            return dato;
        }
    }

    @Override
    public void append(T elemento) throws ListException {
        NodoSimple<T> nodoNuevo = new NodoSimple<>(elemento);
        NodoSimple<T> nodo = inicio;

        if (nodo == null) {
            inicio = nodoNuevo;

        } else {

            while (nodo.sig != null) {
                nodo = nodo.sig;
            }

            nodo.sig = nodoNuevo;

        }

        nElementos++;

    }

    @Override
    public void insert(T elemento, int index) throws ListException {
        NodoSimple<T> nodoNuevo = new NodoSimple<>(elemento);

        if (index < 0 || index > nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        if (index == 0) {
            if (inicio != null) {
                nodoNuevo.sig = inicio;
            }
            inicio = nodoNuevo;
        } else {
            NodoSimple<T> nodo = inicio;

            for (int j = 0; j < index - 1; j++) {
                nodo = nodo.sig;
            }

            nodoNuevo.sig = nodo.sig;

            nodo.sig = nodoNuevo;
        }

        nElementos++;
    }

    @Override
    public T remove(int index) throws ListException {
        T removed;
        if (empty()) {
            throw new ListException("Lista vacia");
        }
        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        if (index == 0) {
            removed = inicio.dato;
            inicio = inicio.sig;
        } else {
            NodoSimple<T> nodo = inicio;
            for (int j = 0; j < index - 1; j++) {
                nodo = nodo.sig;
            }
            removed = nodo.sig.dato;
            nodo.sig = nodo.sig.sig;
        }
        nElementos--;
        return removed;
    }

    public void invertir() {

        NodoSimple<T> anterior = null;
        NodoSimple<T> actual = inicio;

        while (actual != null) {

            NodoSimple<T> siguiente = actual.sig;

            actual.sig = anterior;

            anterior = actual;
            actual = siguiente;
        }

        inicio = anterior;
    }

    public void concatenar(ListaEnlazada<T> otraLista) {

        NodoSimple<T> nodo = otraLista.inicio;
        
        while (nodo != null) {
            append(nodo.dato);
            nodo = nodo.sig;
        }
    }

    @Override
    public boolean removeObj(T elemento) throws ListException {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        NodoSimple<T> nodo = inicio;

        if (nodo.dato.equals(elemento)) {
            inicio = inicio.sig;
            nElementos--;
            return true;
        }

        while (nodo.sig != null) {

            if (nodo.sig.dato.equals(elemento)) {
                nodo.sig = nodo.sig.sig;
                nElementos--;
                return true;
            }

            nodo = nodo.sig;
        }

        return false;
    }

    @Override
    public int indexOf(T elemento) {
        NodoSimple<T> nodo = inicio;
        int index = 0;

        while (nodo != null) {

            if (nodo.dato.equals(elemento)) {
                return index;
            }

            nodo = nodo.sig;
            index++;
        }

        return -1;
    }

    @Override
    public T get(int index) throws ListException {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        NodoSimple<T> nodo = inicio;
        for (int j = 0; j < index; j++) {
            nodo = nodo.sig;
        }
        return nodo.dato;
    }

    @Override
    public void set(T elemento, int index) throws ListException {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        NodoSimple<T> nodo = inicio;

        for (int j = 0; j < index; j++) {
            nodo = nodo.sig;
        }

        nodo.dato = elemento;
    }

    @Override
    public void clear() {
        inicio = null;
        nElementos = 0;
    }

    @Override
    public boolean empty() {
        return inicio == null;
    }

    @Override
    public int size() {
        return nElementos;
    }

    @Override
    public String toString() {
        String s = "[";
        NodoSimple<T> nodo = inicio;
        while (nodo != null) {
            s += nodo.dato.toString();
            if (nodo.sig != null) {
                s += ", ";
            }
            nodo = nodo.sig;
        }
        s += "]";
        return s;
    }

//    @Override
//    public java.util.Iterator<T> Iterator() {
//        return new ListIterator(inicio);
//    }
    @Override
    public java.util.Iterator<T> iterator() {
        return new ListIterator(inicio);
    }

}
