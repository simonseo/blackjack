package test.unit;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import api.Table;
import impl.BlackJackTable;

public class TableTest {
    @Test
    public void testNoMoneyGameOver() {
        Table table = new BlackJackTable(2, 1);
        assertFalse(table.isGameOver());
    }

    @Test
    public void testNoPlayersGameOver() {
        Table table = new BlackJackTable(0, 1);
        assertTrue(table.isGameOver());
    }
}
