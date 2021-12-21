import { Link } from "react-router-dom"
import './admin.css'
const AdminPage = () => {
    return <div className="admin-all">
        <div className="admin_page">
            <Link to='/order-manager' className="admin-link">Order manager</Link><br />
        </div>
        <div className="admin_page">
            <Link to='/product-manager' className="admin-link">Product manager</Link>
        </div>
    </div>
}

export default AdminPage