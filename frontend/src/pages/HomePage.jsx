export default function HomePage() {
  const featureCards = [
    { title: 'Fast checkout', text: 'Streamlined purchase flow for modern shoppers.' },
    { title: 'Inventory insight', text: 'Track stock, pricing, and product health in one place.' },
    { title: 'Secure orders', text: 'Account-first flows with order visibility and trust.' }
  ];

  return (
    <>
      <section className="hero">
        <div>
          <p className="muted">Built for growth</p>
          <h1>Modern commerce for every customer journey.</h1>
          <p>
            A complete storefront and admin platform designed around product discovery,
            secure checkout, and operational clarity.
          </p>
          <div className="hero-actions">
            <a href="/products" className="btn btn-primary">Shop now</a>
            <a href="/register" className="btn btn-secondary">Create account</a>
          </div>
        </div>

        <div className="hero-panel">
          <h2>Today’s performance</h2>
          <p className="muted">Orders processed</p>
          <h3 style={{ fontSize: '2.4rem', margin: '0 0 0.5rem' }}>12,486</h3>
          <p className="muted">Conversion rate</p>
          <h3 style={{ fontSize: '1.8rem', margin: 0 }}>4.8%</h3>
        </div>
      </section>

      <section className="card-grid">
        {featureCards.map((card) => (
          <article key={card.title} className="card">
            <h3>{card.title}</h3>
            <p className="muted">{card.text}</p>
          </article>
        ))}
      </section>
    </>
  );
}
