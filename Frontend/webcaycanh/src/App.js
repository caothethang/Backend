import { Routes, Route } from 'react-router-dom'

import './App.css';
import Login from './pages/login/login';
import SignUp from './pages/signup/signup';
import CartPage from './pages/user/cart/cart';
import HomePage from './pages/user/home/home';
import ProductPage from './pages/user/product/product';
import AdminPage from './pages/admin/admin';
import Order from './pages/order/order';
import ContentAdmin from './component/contentAdmin/contentAdmin';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='/login' element={<Login/>}/>
        <Route path='/signup/' element={<SignUp />} />
        <Route path='/' element={<HomePage />} />
        <Route path='/product' element={<ProductPage/>}/>
        <Route path='/cart' element={<CartPage/>}/>
        <Route path='/admin' element={<AdminPage/>}/>
        <Route path='/product-manager' element={<ContentAdmin/>}/>
        <Route path='/order-manager' element={<Order/>}/>
      </Routes>
    </div>
  );
}

export default App;
