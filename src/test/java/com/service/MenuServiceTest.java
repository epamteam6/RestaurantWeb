package com.service;

import com.dao.DishDAO;
import com.dao.DishTypeDAO;
import com.model.Dish;
import com.model.DishType;
import com.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    private static MenuService service;

    @Mock
    private DishTypeDAO dishTypeDAOMock;
    @Mock
    private DishDAO dishDAOMock;

    private Optional<Order> optOrder;

    private List<Dish> dishes = Arrays.asList(
            new Dish(1, "MOJITO", 1, 100),
            new Dish(2, "BURRITO", 2, 100)
    );
    private List<DishType> dishTypes = Arrays.asList(
            new DishType(1, "DRINKS"),
            new DishType(2, "SHAVERMAS")
    );

    @Before
    public void init() {
        service = MenuService.getInstance();
        service.setDishTypeDAO(dishTypeDAOMock);
        service.setDishDAO(dishDAOMock);
    }

    @Test
    public void getMenu() throws Exception {

        when(dishDAOMock.getAll()).thenReturn(dishes);
        when(dishTypeDAOMock.getAll()).thenReturn(dishTypes);
        when(dishTypeDAOMock.getById(1)).thenReturn(Optional.of(dishTypes.get(0)));
        when(dishTypeDAOMock.getById(2)).thenReturn(Optional.of(dishTypes.get(1)));

        Map<String, Map<String, Long>> menu = service.getMenu();

        String act = menuToString(menu);
        String exp = "{DRINKS={MOJITO=100}, SHAVERMAS={BURRITO=100}}";

        verify(dishDAOMock, atLeastOnce()).getAll();
        verify(dishTypeDAOMock, times(1)).getAll();
        verify(dishTypeDAOMock, times(3)).getById(1);
        verify(dishTypeDAOMock, times(3)).getById(2);

        assertEquals(act, exp);
    }

    private String menuToString(Map<String, Map<String, Long>> menu) {
        return menu.toString();
    }

}