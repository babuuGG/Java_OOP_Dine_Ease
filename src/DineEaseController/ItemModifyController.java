package DineEaseController;

import DineEaseDatabase.ItemModifyDAO;
import DineEaseVIew.ItemModifyView;



public class ItemModifyController {
    private ItemModifyView view;
    private ItemModifyDAO model;

    public ItemModifyController(ItemModifyView view, ItemModifyDAO model) {
        this.view = view;
        this.model = model;
    }

       
}
