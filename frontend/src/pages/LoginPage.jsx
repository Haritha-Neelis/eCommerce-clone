export default function LoginPage() {
  return (
    <div className="form-card">
      <div className="page-header">
        <h1>Welcome back</h1>
        <p className="muted">Sign in to continue to your account.</p>
      </div>

      <form className="form-grid">
        <div>
          <label className="label" htmlFor="email">Email</label>
          <input className="input" id="email" type="email" placeholder="name@example.com" />
        </div>

        <div>
          <label className="label" htmlFor="password">Password</label>
          <input className="input" id="password" type="password" placeholder="••••••••" />
        </div>

        <button type="submit" className="btn btn-primary">Sign in</button>
        <p className="muted">
          Need an account? <a className="topbar-link" href="/register">Create one</a>
        </p>
      </form>
    </div>
  );
}
