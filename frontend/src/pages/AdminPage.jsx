export default function AdminPage() {
  return (
    <div className="form-card">
      <div className="page-header">
        <h1>Admin dashboard</h1>
        <p className="muted">Operations overview and catalog management.</p>
      </div>

      <div className="card-grid">
        <div className="card">
          <h3>Revenue</h3>
          <p className="price">₹84.2L</p>
        </div>
        <div className="card">
          <h3>Orders</h3>
          <p className="price">1,284</p>
        </div>
        <div className="card">
          <h3>Customers</h3>
          <p className="price">8,410</p>
        </div>
      </div>
    </div>
  );
}
