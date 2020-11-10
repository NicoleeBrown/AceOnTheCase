package com.example.aceonthecasev100;

import android.app.Activity;
import android.content.Context;

import android.util.DisplayMetrics;

public class GraphicsToolkit {
    /** GraphicsToolkit for proper display */
    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;

    /** GraphicsToolkit for scene management */
    public static Context CURRENT_CONTEXT;
    public static Activity MAIN_ACTIVITY;

    /** GraphicsToolkit for Puzzle building */
    public static int OFFSET;
    public static int BLOCK_HEIGHT;
    public static int BLOCK_WIDTH;

    public static void initializeConstants(DisplayMetrics dm, Context context, Activity main)
    {
        GraphicsToolkit.SCREEN_HEIGHT = dm.heightPixels;
        GraphicsToolkit.SCREEN_WIDTH = dm.widthPixels;
        // This is used for the puzzles, which have an offset of 100px of each side of the screen
        GraphicsToolkit.OFFSET = 100;
        GraphicsToolkit.BLOCK_WIDTH = (GraphicsToolkit.SCREEN_WIDTH - GraphicsToolkit.OFFSET*2) / 8;
        GraphicsToolkit.BLOCK_HEIGHT = (GraphicsToolkit.SCREEN_HEIGHT - GraphicsToolkit.OFFSET*2) / 5;
        GraphicsToolkit.CURRENT_CONTEXT = context;
        GraphicsToolkit.MAIN_ACTIVITY = main;

    }

    /** Functions for unblock me puzzle */
    public static int getGridPosH(int gridSlot)
    {
        return OFFSET + ((gridSlot*(SCREEN_WIDTH - OFFSET*2))/8);
    }

    public static int getGridPosV(int gridSlot)
    {
        return OFFSET + (gridSlot*(SCREEN_HEIGHT - OFFSET*2)/5);
    }

    public static int findGridPosH(int absolutePos, boolean isPlayer, int size) // This function returns the x position that the block is supposed to snap to.
    {
        int window = BLOCK_WIDTH/2;

        if ((absolutePos + window) <= getGridPosH(1))
            return getGridPosH(0);
        else if ((absolutePos + window) <= getGridPosH(2))
            return getGridPosH(1);
        else if ((absolutePos + window) <= getGridPosH(3))
            return getGridPosH(2);
        else if ((absolutePos + window) <= getGridPosH(4))
            return getGridPosH(3);
        else if ((absolutePos + window) <= getGridPosH(5) || size == 3) // sets limit for a size 3 rectangle
            return getGridPosH(4);
        else if ((absolutePos + window) <= getGridPosH(6) || size == 2) // sets limit for a size 2
            return getGridPosH(5);
        else if ((absolutePos + window) <= getGridPosH(7) || !isPlayer) // sets limit for anything that's not player
            return getGridPosH(6);

        return getGridPosH(7);
    }

    public static int findGridPosV(int absolutePos)
    {
        int window = BLOCK_HEIGHT/2;

        if ((absolutePos + window) <= getGridPosV(1))
            return getGridPosV(0);
        else if ((absolutePos + window) <= getGridPosV(2))
            return getGridPosV(1);
        else if ((absolutePos + window) <= getGridPosV(3))
            return getGridPosV(2);

        return getGridPosV(3);
    }
}
