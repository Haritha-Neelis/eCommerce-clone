import { useNavigate } from 'react-router-dom';
import { useShop } from '../context/ShopContext';

export default function CheckoutPage() {
  const navigate = useNavigate();
  const { cart, cartTotal, clearCart, setLastOrder } = useShop();

  if (cart.length === 0) {
    return (
      <div className="form-card empty-state">
        <span className="success-icon">✓</span>
        <h1>Nothing to checkout</h1>
        <p className="muted">Your cart is empty. Find something you love first.</p>
        <button className="btn btn-primary" type="button" onClick={() => navigate('/products')}>Browse products</button>
      </div>
    );
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    setLastOrder({
      id: `CF-${Date.now().toString().slice(-6)}`,
      total: cartTotal,
      itemCount: cart.reduce((sum, item) => sum + item.quantity, 0),
      date: new Date().toLocaleDateString('en-IN', { day: 'numeric', month: 'short', year: 'numeric' })
    });
    clearCart();
    navigate('/account?order=placed');
  };

  return (
    <div className="checkout-layout">
      <div className="form-card">
        <div className="page-header">
          <h1>Checkout</h1>
          <p className="muted">Enter shipping and payment details.</p>
        </div>

        <form className="form-grid" onSubmit={handleSubmit}>
          <div>
            <label className="label" htmlFor="address">Shipping address</label>
            <textarea className="input" id="address" rows="4" defaultValue="123 Market Street, Bengaluru, Karnataka" />
          </div>
          <div>
            <label className="label" htmlFor="card">Card number</label>
            <input className="input" id="card" type="text" placeholder="4242 4242 4242 4242" defaultValue="4242 4242 4242 4242" />
          </div>
          <button type="submit" className="btn btn-primary">Place order</button>
        </form>
      </div>

      <aside className="summary-card">
        <h2>Order summary</h2>
        {cart.map((item) => (
          <div key={item.id} className="summary-row">
            <span>{item.name} × {item.quantity}</span>
            <strong>₹{(item.price * item.quantity).toLocaleString('en-IN')}</strong>
          </div>
        ))}
        <div className="summary-row total-row">
          <span>Total</span>
          <strong>₹{cartTotal.toLocaleString('en-IN')}</strong>
        </div>
      </aside>
    </div>
  );
}
