const products = [
  { id: 1, name: 'Aurora Headphones', price: '$129.00' },
  { id: 2, name: 'Nimbus Lamp', price: '$89.00' },
  { id: 3, name: 'Peak Bottle', price: '$34.00' },
  { id: 4, name: 'Arc Keyboard', price: '$149.00' }
];

export default function ProductsPage() {
  return (
    <>
      <div className="page-header">
        <h1>Shop collection</h1>
        <p className="muted">Curated essentials for everyday productivity and lifestyle.</p>
      </div>

      <div className="product-grid">
        {products.map((product) => (
          <article key={product.id} className="product-card">
            <div className="product-image" aria-label={product.name} />
            <div className="product-body">
              <h3>{product.name}</h3>
              <div className="price">{product.price}</div>
              <p className="muted">Free shipping on eligible orders.</p>
              <a href={`/products/${product.id}`} className="btn btn-secondary" style={{ marginTop: '0.5rem' }}>
                View details
              </a>
            </div>
          </article>
        ))}
      </div>
    </>
  );
}
