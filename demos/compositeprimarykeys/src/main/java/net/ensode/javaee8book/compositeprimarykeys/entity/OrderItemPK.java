package net.ensode.javaee8book.compositeprimarykeys.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemPK implements Serializable {
  public Long orderId;
  public Long itemId;

  public OrderItemPK() {

  }

  public OrderItemPK(Long orderId, Long itemId) {
    this.orderId = orderId;
    this.itemId = itemId;
  }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.orderId);
        hash = 97 * hash + Objects.hashCode(this.itemId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final OrderItemPK other = (OrderItemPK) obj;

        if (!Objects.equals(this.orderId, other.orderId)) {
            return false;
        }

        if (!Objects.equals(this.itemId, other.itemId)) {
            return false;
        }

        return true;
    }


}