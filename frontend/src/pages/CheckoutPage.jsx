export default function CheckoutPage() {
  return (
    <div className="form-card">
      <div className="page-header">
        <h1>Checkout</h1>
        <p className="muted">Enter shipping and payment details.</p>
      </div>

      <form className="form-grid">
        <div>
          <label className="label" htmlFor="address">Shipping address</label>
          <textarea className="input" id="address" rows="4" defaultValue="123 Market Street, Austin, TX" />
        </div>
        <div>
          <label className="label" htmlFor="card">Card number</label>
          <input className="input" id="card" type="text" placeholder="4242 4242 4242 4242" />
        </div>
        <button type="submit" className="btn btn-primary">Place order</button>
      </form>
    </div>
  );
}
