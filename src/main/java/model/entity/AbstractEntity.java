package model.entity;

import viewModel.ViewModel;

public abstract class AbstractEntity {
    public int x;
    public int y;

    public AbstractEntity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public abstract void update();
}
