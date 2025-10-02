import React, { useEffect, useState, useContext } from 'react';
import Navbar from '../components/Navbar';
import api, { advertisementService } from '../services/api';
import './Profile.css';
import { AuthContext } from '../context/AuthContext';

const Profile = () => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [myAds, setMyAds] = useState([]);
  const [myRentedAds, setMyRentedAds] = useState([]);
  const [adsLoading, setAdsLoading] = useState(true);
  const [activeTab, setActiveTab] = useState('profile');
  const [editMode, setEditMode] = useState(false);
  const [editForm, setEditForm] = useState({});
  const { isLoggedIn } = useContext(AuthContext);

  // Token'dan username'i decode etmek için basit bir JWT decode fonksiyonu
  function parseJwt(token) {
    try {
      return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
      return null;
    }
  }

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = localStorage.getItem('token');
        let username = '';
        if (token) {
          const decoded = parseJwt(token);
          username = decoded?.sub;
        }
        if (!username) {
          setUser(null);
          setLoading(false);
          return;
        }
        // Query parametresi ile GET isteği
        const response = await api.get(`/user/profile?username=${username}`);
        setUser(response.data);
      } catch (error) {
        setUser(null);
      } finally {
        setLoading(false);
      }
    };

    const fetchAds = async () => {
      try {
        console.log('İlanlar yükleniyor...');
        const [myAdsRes, myRentedAdsRes] = await Promise.all([
          advertisementService.getMyAds(),
          advertisementService.getMyRentedAds()
        ]);
        
        console.log('My Ads Response:', myAdsRes);
        console.log('My Rented Ads Response:', myRentedAdsRes);
        
        setMyAds(myAdsRes.data || []);
        setMyRentedAds(myRentedAdsRes.data || []);
      } catch (error) {
        console.error('İlanlar yüklenirken hata oluştu:', error);
        console.error('Error details:', error.response?.data);
        setMyAds([]);
        setMyRentedAds([]);
      } finally {
        setAdsLoading(false);
      }
    };

    fetchProfile();
    fetchAds();
  }, []);

  const getSafeImageUrl = (url) => {
    const placeholder = 'https://res.cloudinary.com/dqtkblhwr/image/upload/v1720000000/placeholder_car.png';
    if (!url || typeof url !== 'string') return placeholder;
    let u = url.trim();
    if (u.startsWith('http://')) u = u.replace('http://', 'https://');
    if (!u.startsWith('http')) {
      u = `https://res.cloudinary.com/dqtkblhwr/image/upload/${u}`;
    }
    return u;
  };

  const handleEditProfile = () => {
    setEditForm({
      first_name: user.first_name,
      last_name: user.last_name,
      email: user.email,
      phone_number: user.phone_number
    });
    setEditMode(true);
  };

  const handleSaveProfile = async () => {
    try {
      const token = localStorage.getItem('token');
      const decoded = parseJwt(token);
      const response = await api.put(`/user/profile?username=${decoded.sub}`, editForm);
      setUser(response.data);
      setEditMode(false);
      alert('Profil başarıyla güncellendi!');
    } catch (error) {
      console.error('Profil güncellenirken hata oluştu:', error);
      alert('Profil güncellenirken bir hata oluştu.');
    }
  };

  const handleCancelEdit = () => {
    setEditMode(false);
    setEditForm({});
  };

  const handleDeleteAd = async (adId) => {
    if (window.confirm('Bu ilanı silmek istediğinizden emin misiniz?')) {
      try {
        await advertisementService.deleteAd(adId);
        setMyAds(myAds.filter(ad => ad.ad_id !== adId));
        alert('İlan başarıyla silindi!');
      } catch (error) {
        console.error('İlan silinirken hata oluştu:', error);
        alert('İlan silinirken bir hata oluştu.');
      }
    }
  };

  const handleCancelRent = async (adId) => {
    if (window.confirm('Kiracıyı çıkarmak istediğinizden emin misiniz?')) {
      try {
        console.log('Kiralama iptal ediliyor, AdId:', adId);
        const response = await advertisementService.cancelRent(adId);
        console.log('Cancel rent response:', response);
        
        // İlanları yeniden yükle
        const myAdsRes = await advertisementService.getMyAds();
        console.log('Updated ads:', myAdsRes.data);
        setMyAds(myAdsRes.data || []);
        alert('Kiralama başarıyla iptal edildi!');
      } catch (error) {
        console.error('Kiralama iptal edilirken hata oluştu:', error);
        console.error('Error response:', error.response?.data);
        console.error('Error status:', error.response?.status);
        alert(`Kiralama iptal edilirken bir hata oluştu: ${error.response?.data?.message || error.message}`);
      }
    }
  };

  if (loading) {
    return (
      <div className="profile-page">
        <Navbar siteName="Nereye" />
        <div className="profile-container">
          <h2>Profil</h2>
          <p>Yükleniyor...</p>
        </div>
      </div>
    );
  }

  if (!user) {
    return (
      <div className="profile-page">
        <Navbar siteName="Nereye" />
        <div className="profile-container">
          <h2>Profil</h2>
          <p>Profil bilgileri alınamadı.</p>
        </div>
      </div>
    );
  }

  return (
    <div className="profile-page">
      <div className="profile-bg-overlay"></div>
      <Navbar siteName="Nereye" />
      
      <div className="profile-container">
        {/* Profile Header */}
        <div className="profile-header">
          <div className="profile-avatar">
            <i className="fas fa-user"></i>
          </div>
          <h1 className="profile-welcome">Hoş Geldin, {user.first_name}!</h1>
          <p className="profile-subtitle">Profil bilgilerini yönet ve ilanlarını görüntüle</p>
        </div>

        {/* Navigation Tabs */}
        <div className="profile-tabs">
          <button 
            className={`tab-button ${activeTab === 'profile' ? 'active' : ''}`}
            onClick={() => setActiveTab('profile')}
          >
            <i className="fas fa-user"></i>
            Profil Bilgileri
          </button>
          <button 
            className={`tab-button ${activeTab === 'ads' ? 'active' : ''}`}
            onClick={() => setActiveTab('ads')}
          >
            <i className="fas fa-car"></i>
            İlanlarım
          </button>
          <button 
            className={`tab-button ${activeTab === 'rentals' ? 'active' : ''}`}
            onClick={() => setActiveTab('rentals')}
          >
            <i className="fas fa-handshake"></i>
            Kiraladıklarım
          </button>
        </div>

        {/* Profile Information Tab */}
        {activeTab === 'profile' && (
          <div className="profile-content">
            <div className="profile-card">
              <div className="profile-card-header">
                <h3>Kişisel Bilgiler</h3>
                {!editMode && (
                  <button className="edit-button" onClick={handleEditProfile}>
                    <i className="fas fa-edit"></i>
                    Düzenle
                  </button>
                )}
              </div>
              
              {editMode ? (
                <div className="edit-form">
                  <div className="form-group">
                    <label>Ad:</label>
                    <input 
                      type="text" 
                      value={editForm.first_name || ''}
                      onChange={(e) => setEditForm({...editForm, first_name: e.target.value})}
                    />
                  </div>
                  <div className="form-group">
                    <label>Soyad:</label>
                    <input 
                      type="text" 
                      value={editForm.last_name || ''}
                      onChange={(e) => setEditForm({...editForm, last_name: e.target.value})}
                    />
                  </div>
                  <div className="form-group">
                    <label>E-posta:</label>
                    <input 
                      type="email" 
                      value={editForm.email || ''}
                      onChange={(e) => setEditForm({...editForm, email: e.target.value})}
                    />
                  </div>
                  <div className="form-group">
                    <label>Telefon:</label>
                    <input 
                      type="tel" 
                      value={editForm.phone_number || ''}
                      onChange={(e) => setEditForm({...editForm, phone_number: e.target.value})}
                    />
                  </div>
                  <div className="form-actions">
                    <button className="save-button" onClick={handleSaveProfile}>
                      <i className="fas fa-save"></i>
                      Kaydet
                    </button>
                    <button className="cancel-button" onClick={handleCancelEdit}>
                      <i className="fas fa-times"></i>
                      İptal
                    </button>
                  </div>
                </div>
              ) : (
                <div className="profile-info">
                  <div className="info-item">
                    <div className="info-icon">
                      <i className="fas fa-user"></i>
                    </div>
                    <div className="info-content">
                      <span className="info-label">Kullanıcı Adı</span>
                      <span className="info-value">{user.user_name}</span>
                    </div>
                  </div>
                  <div className="info-item">
                    <div className="info-icon">
                      <i className="fas fa-signature"></i>
                    </div>
                    <div className="info-content">
                      <span className="info-label">Ad Soyad</span>
                      <span className="info-value">{user.first_name} {user.last_name}</span>
                    </div>
                  </div>
                  <div className="info-item">
                    <div className="info-icon">
                      <i className="fas fa-envelope"></i>
                    </div>
                    <div className="info-content">
                      <span className="info-label">E-posta</span>
                      <span className="info-value">{user.email}</span>
                    </div>
                  </div>
                  <div className="info-item">
                    <div className="info-icon">
                      <i className="fas fa-phone"></i>
                    </div>
                    <div className="info-content">
                      <span className="info-label">Telefon</span>
                      <span className="info-value">{user.phone_number}</span>
                    </div>
                  </div>
                </div>
              )}
            </div>

            <div className="profile-stats">
              <div className="stat-card">
                <div className="stat-icon">
                  <i className="fas fa-car"></i>
                </div>
                <div className="stat-content">
                  <span className="stat-number">{myAds.length}</span>
                  <span className="stat-label">Toplam İlan</span>
                </div>
              </div>
              <div className="stat-card">
                <div className="stat-icon">
                  <i className="fas fa-handshake"></i>
                </div>
                <div className="stat-content">
                  <span className="stat-number">{myRentedAds.length}</span>
                  <span className="stat-label">Kiraladığım</span>
                </div>
              </div>
              <div className="stat-card">
                <div className="stat-icon">
                  <i className="fas fa-check-circle"></i>
                </div>
                <div className="stat-content">
                  <span className="stat-number">{myAds.filter(ad => ad.tenet_id).length}</span>
                  <span className="stat-label">Aktif Kiralama</span>
                </div>
              </div>
            </div>
          </div>
        )}

        {/* My Ads Tab */}
        {activeTab === 'ads' && (
          <div className="profile-content">
            <div className="profile-section">
              <div className="section-header">
                <h3>İlanlarım ({myAds.length})</h3>
                <div style={{ display: 'flex', gap: '10px' }}>
                  <button className="add-ad-button" onClick={() => {
                    console.log('Current user:', user);
                    console.log('My ads:', myAds);
                    console.log('Loading state:', adsLoading);
                  }}>
                    <i className="fas fa-bug"></i>
                    Debug
                  </button>
                  <button className="add-ad-button" onClick={() => window.location.href = '/create-advertisement'}>
                    <i className="fas fa-plus"></i>
                    Yeni İlan
                  </button>
                </div>
              </div>
              
              {adsLoading ? (
                <div className="loading-state">
                  <i className="fas fa-spinner fa-spin"></i>
                  <p>İlanlar yükleniyor...</p>
                </div>
              ) : myAds.length === 0 ? (
                <div className="empty-state">
                  <i className="fas fa-car"></i>
                  <h4>Henüz ilan vermemişsiniz</h4>
                  <p>İlk ilanınızı oluşturmak için butona tıklayın</p>
                  <button className="cta-button" onClick={() => window.location.href = '/create-advertisement'}>
                    İlan Ver
                  </button>
                </div>
              ) : (
                <div className="ads-grid">
                  {myAds.map((ad) => {
                    const name = `${ad.car?.brand?.brand_name || ad.car?.brand?.name || ''} ${ad.car?.model || ''}`.trim();
                    return (
                      <div key={ad.ad_id} className="ad-card">
                        <div className="ad-image-container">
                          <img
                            src={getSafeImageUrl(ad.car?.img_url)}
                            alt={name}
                            className="ad-image"
                            onError={(e) => { e.currentTarget.src = getSafeImageUrl(''); }}
                          />
                          <div className="ad-actions">
                            {ad.tenet_id && (
                              <button 
                                className="cancel-rent-button" 
                                onClick={() => handleCancelRent(ad.ad_id)}
                                title="Kiralama İptal"
                              >
                                <i className="fas fa-user-minus"></i>
                              </button>
                            )}
                            <button 
                              className="delete-ad-button" 
                              onClick={() => handleDeleteAd(ad.ad_id)}
                              title="İlanı Sil"
                            >
                              <i className="fas fa-trash-alt"></i>
                            </button>
                          </div>
                        </div>
                        <div className="ad-details">
                          <h4>{name}</h4>
                          <div className="ad-info">
                            <span className="ad-year">{ad.car?.year}</span>
                            <span className="ad-gear">{ad.car?.gear_type ? 'Otomatik' : 'Manuel'}</span>
                          </div>
                          <p className="ad-price">{ad.daily_price} TL <span>/ günlük</span></p>
                          <div className={`ad-status ${ad.tenet_id ? 'rented' : 'available'}`}>
                            <i className={`fas ${ad.tenet_id ? 'fa-lock' : 'fa-check-circle'}`}></i>
                            {ad.tenet_id ? 'Kiralanmış' : 'Müsait'}
                          </div>
                        </div>
                      </div>
                    );
                  })}
                </div>
              )}
            </div>
          </div>
        )}

        {/* My Rentals Tab */}
        {activeTab === 'rentals' && (
          <div className="profile-content">
            <div className="profile-section">
              <h3>Kiraladığım Araçlar</h3>
              
              {adsLoading ? (
                <div className="loading-state">
                  <i className="fas fa-spinner fa-spin"></i>
                  <p>Kiraladığınız araçlar yükleniyor...</p>
                </div>
              ) : myRentedAds.length === 0 ? (
                <div className="empty-state">
                  <i className="fas fa-handshake"></i>
                  <h4>Henüz araç kiralamamışsınız</h4>
                  <p>Ana sayfadan bir araç kiralayarak başlayın</p>
                  <button className="cta-button" onClick={() => window.location.href = '/'}>
                    Araç Ara
                  </button>
                </div>
              ) : (
                <div className="ads-grid">
                  {myRentedAds.map((ad) => {
                    const name = `${ad.car?.brand?.brand_name || ad.car?.brand?.name || ''} ${ad.car?.model || ''}`.trim();
                    return (
                      <div key={ad.ad_id} className="ad-card">
                        <img
                          src={getSafeImageUrl(ad.car?.img_url)}
                          alt={name}
                          className="ad-image"
                          onError={(e) => { e.currentTarget.src = getSafeImageUrl(''); }}
                        />
                        <div className="ad-details">
                          <h4>{name}</h4>
                          <div className="ad-info">
                            <span className="ad-year">{ad.car?.year}</span>
                            <span className="ad-gear">{ad.car?.gear_type ? 'Otomatik' : 'Manuel'}</span>
                          </div>
                          <p className="ad-price">{ad.daily_price} TL <span>/ günlük</span></p>
                          <div className="ad-status rented">
                            <i className="fas fa-handshake"></i>
                            Kiralanmış
                          </div>
                        </div>
                      </div>
                    );
                  })}
                </div>
              )}
            </div>
          </div>
        )}

        {/* Logout Button */}
        <div className="profile-footer">
          <button 
            className="logout-button" 
            onClick={() => { 
              if(window.confirm('Çıkış yapmak istiyor musunuz?')) { 
                localStorage.removeItem('token'); 
                window.location.href = '/login'; 
              } 
            }}
          >
            <i className="fas fa-sign-out-alt"></i>
            Çıkış Yap
          </button>
        </div>
      </div>
    </div>
  );
};

export default Profile;
