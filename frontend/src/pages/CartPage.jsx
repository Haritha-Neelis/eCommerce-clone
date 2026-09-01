export default function CartPage() {
  return (
    <div className="form-card">
      <div className="page-header">
        <h1>Your cart</h1>
        <p className="muted">Review your selections before checkout.</p>
      </div>

      <div className="card">
        <p><strong>Aurora Headphones</strong> — $129.00</p>
        <p><strong>Peak Bottle</strong> — $34.00</p>
        <hr />
        <p><strong>Total:</strong> $163.00</p>
        <a href="/checkout" className="btn btn-primary">Proceed to checkout</a>
      </div>
    </div>
  );
}
