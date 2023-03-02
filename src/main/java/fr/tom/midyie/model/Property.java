/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 22:21
 */

package fr.tom.midyie.model;

public class Property {

    private int id;
    private boolean isBreakable;
    private boolean isFlammable;
    private boolean isStackable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsBreakable() {
        return isBreakable;
    }

    public void setIsBreakable(boolean isBreakable) {
        this.isBreakable = isBreakable;
    }

    public boolean getIsFlammable() {
        return isFlammable;
    }

    public void setIsFlammable(boolean isFlammable) {
        this.isFlammable = isFlammable;
    }

    public boolean getIsStackable() {
        return isStackable;
    }

    public void setIsStackable(boolean isStackable) {
        this.isStackable = isStackable;
    }
}
