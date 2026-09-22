import { Link } from 'react-router-dom';
import { useShop } from '../context/ShopContext';

export default function CartPage() {
  const { cart, updateCartQuantity, removeFromCart, cartTotal } = useShop();

  if (cart.length === 0) {
    return (
      <div className="form-card empty-state">
        <h1>Your cart is empty</h1>
        <p className="muted">Add products to your cart and they will appear here.</p>
        <Link to="/products" className="btn btn-primary">Continue shopping</Link>
      </div>
    );
  }

  return (
    <div className="cart-layout">
      <div className="form-card cart-list">
        <div className="page-header">
          <h1>Your cart</h1>
          <p className="muted">Review your selections before checkout.</p>
        </div>

        {cart.map((item) => (
          <div key={item.id} className="cart-item">
            <img src={item.image} alt={item.name} className="cart-thumb" />
            <div className="cart-item-body">
              <h3>{item.name}</h3>
              <p className="muted">₹{item.price.toLocaleString('en-IN')}</p>
              <div className="qty-row">
                <button type="button" onClick={() => updateCartQuantity(item.id, item.quantity - 1)}>-</button>
                <span>{item.quantity}</span>
                <button type="button" onClick={() => updateCartQuantity(item.id, item.quantity + 1)}>+</button>
              </div>
            </div>
            <button className="text-button" type="button" onClick={() => removeFromCart(item.id)}>Remove</button>
          </div>
        ))}
      </div>

      <aside className="summary-card">
        <h2>Order summary</h2>
        <div className="summary-row">
          <span>Subtotal</span>
          <strong>₹{cartTotal.toLocaleString('en-IN')}</strong>
        </div>
        <div className="summary-row">
          <span>Shipping</span>
          <strong>Free</strong>
        </div>
        <div className="summary-row total-row">
          <span>Total</span>
          <strong>₹{cartTotal.toLocaleString('en-IN')}</strong>
        </div>
        <Link to="/checkout" className="btn btn-primary full-width">Proceed to checkout</Link>
      </aside>
    </div>
  );
}
