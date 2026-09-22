import { useNavigate } from 'react-router-dom';
import { useShop } from '../context/ShopContext';

export default function CheckoutPage() {
  const navigate = useNavigate();
  const { cart, cartTotal } = useShop();

  const handleSubmit = (event) => {
    event.preventDefault();
    navigate('/account');
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
