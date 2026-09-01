export default function AccountPage() {
  return (
    <div className="form-card">
      <div className="page-header">
        <h1>My account</h1>
        <p className="muted">Manage profile, addresses, and order history.</p>
      </div>

      <div className="card-grid">
        <div className="card">
          <h3>Profile</h3>
          <p className="muted">Jane Doe</p>
        </div>
        <div className="card">
          <h3>Addresses</h3>
          <p className="muted">2 saved addresses</p>
        </div>
        <div className="card">
          <h3>Orders</h3>
          <p className="muted">6 recent orders</p>
        </div>
      </div>
    </div>
  );
}
