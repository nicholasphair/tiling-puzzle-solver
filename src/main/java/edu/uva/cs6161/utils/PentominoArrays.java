package edu.uva.cs6161.utils;

import java.util.ArrayList;
import java.util.List;

public final class PentominoArrays {
    public static final char[][] F = {
            {'_', 'X', 'X'},
            {'X', 'X', '_'},
            {'_', 'X', '_'},
    };

    public static final char[][] L = {
            {'X', '_'},
            {'X', '_'},
            {'X', '_'},
            {'X', 'X'},
    };

    public static final char[][] N = {
            {'X', '_'},
            {'X', '_'},
            {'X', 'X'},
            {'_', 'X'},
    };

    public static final char[][] P = {
            {'X', 'X'},
            {'X', 'X'},
            {'X', '_'},
    };

    public static final char[][] Y = {
            {'_', 'X', '_', 'X'},
            {'_', '_', 'X', '_'},
            {'_', 'X', '_', '_'},
            {'X', '_', '_', '_'},
    };

    public static final char[][] T = {
            {'X', 'X', 'X'},
            {'_', 'X', '_'},
            {'_', 'X', '_'},
    };

    public static final char[][] U = {
            {'X', '_', 'X'},
            {'X', 'X', 'X'},
    };

    public static final char[][] V = {
            {'_', '_', 'X'},
            {'_', '_', 'X'},
            {'X', 'X', 'X'},
    };

    public static final char[][] W = {
            {'X', '_', 'X', '_', 'X'},
            {'_', 'X', '_', 'X', '_'},
    };

    public static final char[][] Z = {
            {'X', 'X', '_'},
            {'_', 'X', '_'},
            {'_', 'X', 'X'},
    };

    public static final char[][] I = {
            {'X'},
            {'X'},
            {'X'},
            {'X'},
            {'X'},
    };

    public static final char[][] X = {
            {'X', '_', 'X'},
            {'_', 'X', '_'},
            {'X', '_', 'X'},
    };

    public static final List<char[][]> ALL_PENTOMINOS= new ArrayList<>();
    static {
        ALL_PENTOMINOS.add(F);
        ALL_PENTOMINOS.add(L);
        ALL_PENTOMINOS.add(N);
        ALL_PENTOMINOS.add(P);
        ALL_PENTOMINOS.add(Y);
        ALL_PENTOMINOS.add(T);
        ALL_PENTOMINOS.add(U);
        ALL_PENTOMINOS.add(V);
        ALL_PENTOMINOS.add(W);
        ALL_PENTOMINOS.add(Z);
        ALL_PENTOMINOS.add(I);
        ALL_PENTOMINOS.add(X);
    }

}
