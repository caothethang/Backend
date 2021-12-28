import { Link } from 'react-router-dom'
import './item.css'

const Item = (props) => {
    const handleClickBought = (e, id) => {
        localStorage.setItem('product_cart', JSON.stringify(id))
    }

    return(
        <div className='item' key={props.data.id}>
            <img src={props.data.image_uri} className='item__image' alt='#'></img>
            <h4 className='item__title'>{props.data.name}</h4>
            <p className='item__cost'>Giá tiền: {props.data.price} vnđ</p>
            <Link to='/cart' className='btn__bought' onClick={(e) => {handleClickBought(e, props.data.id)}}>Đặt ngay</Link>
        </div>
    )
}

export default Item