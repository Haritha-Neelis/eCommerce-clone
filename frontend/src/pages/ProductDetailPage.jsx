export default function ProductDetailPage() {
  return (
    <div className="form-card">
      <div className="page-header">
        <h1>Aurora Headphones</h1>
        <p className="muted">High-fidelity audio with adaptive noise cancellation.</p>
      </div>

      <div className="product-image" style={{ borderRadius: '18px', marginBottom: '1rem' }} />

      <div className="price">$129.00</div>
      <p className="muted">Engineered for immersive listening and all-day comfort.</p>

      <div className="hero-actions" style={{ marginTop: '1rem' }}>
        <button className="btn btn-primary" type="button">Add to cart</button>
        <button className="btn btn-secondary" type="button">Save for later</button>
      </div>
    </div>
  );
}
