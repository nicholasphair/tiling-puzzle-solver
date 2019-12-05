package edu.uva.cs6161.utils;

import edu.uva.cs6161.structures.Enclosure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PentominoUtils {
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
            {'_', '_', 'X', '_'},
            {'X', 'X', 'X', 'X'},
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
            {'_', '_', 'X'},
            {'_', 'X', 'X'},
            {'X', 'X', '_'},
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
            {'_', 'X', '_'},
            {'X', 'X', 'X'},
            {'_', 'X', '_'},
    };

    public static final List<char[][]> ALL_PENTOMINOS;
    public static final List<Enclosure> ALL_PENTOMINO_ENCLOSURES;
    public static final Map<char[][], String> PENTOMINO_FRIENDLY_NAMES;

    static {
        PENTOMINO_FRIENDLY_NAMES = new HashMap<>();
        PENTOMINO_FRIENDLY_NAMES.put(F, "F");
        PENTOMINO_FRIENDLY_NAMES.put(L, "L");
        PENTOMINO_FRIENDLY_NAMES.put(N, "N");
        PENTOMINO_FRIENDLY_NAMES.put(P, "P");
        PENTOMINO_FRIENDLY_NAMES.put(Y, "Y");
        PENTOMINO_FRIENDLY_NAMES.put(T, "T");
        PENTOMINO_FRIENDLY_NAMES.put(U, "U");
        PENTOMINO_FRIENDLY_NAMES.put(V, "V");
        PENTOMINO_FRIENDLY_NAMES.put(W, "W");
        PENTOMINO_FRIENDLY_NAMES.put(Z, "Z");
        PENTOMINO_FRIENDLY_NAMES.put(I, "I");
        PENTOMINO_FRIENDLY_NAMES.put(X, "X");

        ALL_PENTOMINOS = new ArrayList<>();
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

        ALL_PENTOMINO_ENCLOSURES = new ArrayList<>();
        Enclosure enclosureF = new Enclosure(F);
        enclosureF.setName(PENTOMINO_FRIENDLY_NAMES.get(F));
        Enclosure enclosureL = new Enclosure(L);
        enclosureL.setName(PENTOMINO_FRIENDLY_NAMES.get(L));
        Enclosure enclosureN = new Enclosure(N);
        enclosureN.setName(PENTOMINO_FRIENDLY_NAMES.get(N));
        Enclosure enclosureP = new Enclosure(P);
        enclosureP.setName(PENTOMINO_FRIENDLY_NAMES.get(P));
        Enclosure enclosureY = new Enclosure(Y);
        enclosureY.setName(PENTOMINO_FRIENDLY_NAMES.get(Y));
        Enclosure enclosureT = new Enclosure(T);
        enclosureT.setName(PENTOMINO_FRIENDLY_NAMES.get(T));
        Enclosure enclosureU = new Enclosure(U);
        enclosureU.setName(PENTOMINO_FRIENDLY_NAMES.get(U));
        Enclosure enclosureV = new Enclosure(V);
        enclosureV.setName(PENTOMINO_FRIENDLY_NAMES.get(V));
        Enclosure enclosureW = new Enclosure(W);
        enclosureW.setName(PENTOMINO_FRIENDLY_NAMES.get(W));
        Enclosure enclosureZ = new Enclosure(Z);
        enclosureZ.setName(PENTOMINO_FRIENDLY_NAMES.get(Z));
        Enclosure enclosureI = new Enclosure(I);
        enclosureI.setName(PENTOMINO_FRIENDLY_NAMES.get(I));
        Enclosure enclosureX = new Enclosure(X);
        enclosureX.setName(PENTOMINO_FRIENDLY_NAMES.get(X));

        ALL_PENTOMINO_ENCLOSURES.add(enclosureF);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureL);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureN);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureP);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureY);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureT);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureU);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureV);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureW);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureZ);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureI);
        ALL_PENTOMINO_ENCLOSURES.add(enclosureX);
    }

}
