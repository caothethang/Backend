import './footer.css'

const Footer = () => {
    return (
        <div className="footer">
            <div className='footer__content'>
                <div className='footer__contact'>
                    <img src="https://totorogarden.com/wp-content/uploads/2020/04/rsz_logo-01.png" />
                    <p>Totoro garden cung cấp đầy đủ cây cảnh mini, Sen đá-XR, cây nội thất tiểu cảnh - terarium- cây để bàn cho không gian sống của bạn.</p>
                    <p>Liên hệ : 086.246.5789</p>
                    <p>Địa chỉ : Nhà D3, ngõ 88 Trung Kính, Cầu giấy,Hà Nội</p>
                </div>
                <div className='footer__social'>
                    <h3>FACEBOOK</h3>
                    <img className='img-fb' src="https://totorogarden.com/wp-content/uploads/2020/04/860c3629ca60313e6871-1024x683.jpg"/>
                </div>
                <div className='footer__policy'>
                    <h3>CHÍNH SÁCH</h3>
                    <ul className='nav__policy'>
                        <li>Hướng dẫn thanh toán</li>
                        <li>Chính sách bảo hành</li>
                        <li>Quy định đổi hàng</li>
                        <li>Chính sách vận chuyển</li>
                        <li>Bảo mật thông tin</li>
                    </ul>
                </div>
            </div>

        </div>
    )
}

export default Footer