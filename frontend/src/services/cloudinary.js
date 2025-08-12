import { Cloudinary } from '@cloudinary/url-gen';

const cld = new Cloudinary({
  cloud: {
    cloudName: 'dqtkblhwr' 
  }
});

export const uploadImage = async (file) => {
  try {
    // Dosya türü kontrolü
    if (!file || !file.type.startsWith('image/')) {
      throw new Error('Geçerli bir resim dosyası seçin');
    }

 
    const maxSize = 5 * 1024 * 1024; // 5MB
    if (file.size > maxSize) {
      throw new Error('Dosya boyutu 5MB\'dan küçük olmalıdır');
    }

    
    const formData = new FormData();
    formData.append('file', file);
    
    formData.append('upload_preset', 'Nereye'); // Cloudinary dashboard'unda bulunan preset adı
   // Cloudinary'ye yükle
    const response = await fetch(
      `https://api.cloudinary.com/v1_1/dqtkblhwr/image/upload`,
      {
        method: 'POST',
        body: formData
      }
    );

    if (!response.ok) {
      const errorData = await response.json();
      console.error('Cloudinary error details:', errorData);
      
      // Daha detaylı hata mesajları
      if (response.status === 400) {
        throw new Error('Geçersiz dosya formatı veya boyutu');
      } else if (response.status === 401) {
        throw new Error('Cloudinary kimlik doğrulama hatası - upload_preset kontrol edin');
      } else if (response.status === 413) {
        throw new Error('Dosya boyutu çok büyük');
      } else {
        throw new Error(`Resim yükleme başarısız: ${response.status} ${response.statusText}`);
      }
    }

    const data = await response.json();
    console.log('Upload successful:', data);
    
    // Sadece URL'yi döndür (React component'inde kullanım için)
    return data.secure_url;
    
  } catch (error) {
    console.error('Cloudinary yükleme hatası:', error);
    throw error;
  }
};

// Tüm resim bilgilerini almak için ayrı fonksiyon
export const uploadImageWithDetails = async (file) => {
  try {
    // Dosya türü kontrolü
    if (!file || !file.type.startsWith('image/')) {
      throw new Error('Geçerli bir resim dosyası seçin');
    }

    // Dosya boyutu kontrolü (5MB limit)
    const maxSize = 5 * 1024 * 1024; // 5MB
    if (file.size > maxSize) {
      throw new Error('Dosya boyutu 5MB\'dan küçük olmalıdır');
    }

    // FormData oluştur
    const formData = new FormData();
    formData.append('file', file);
    formData.append('upload_preset', 'Nereye');

    // Cloudinary'ye yükle
    const response = await fetch(
      `https://api.cloudinary.com/v1_1/dqtkblhwr/image/upload`,
      {
        method: 'POST',
        body: formData
      }
    );

    if (!response.ok) {
      const errorData = await response.json();
      console.error('Cloudinary error details:', errorData);
      throw new Error(`Resim yükleme başarısız: ${response.status}`);
    }

    const data = await response.json();
    console.log('Upload successful with details:', data);
    
    // Tüm bilgileri döndür
    return {
      secure_url: data.secure_url,
      public_id: data.public_id,
      width: data.width,
      height: data.height,
      format: data.format,
      bytes: data.bytes
    };
    
  } catch (error) {
    console.error('Cloudinary yükleme hatası:', error);
    throw error;
  }
};

// Resim URL'ini optimize etmek için yardımcı fonksiyon
export const getOptimizedImageUrl = (publicId, options = {}) => {
  const defaultOptions = {
    fetch_format: 'auto',
    quality: 'auto',
    ...options
  };
  
  return cld.image(publicId).toURL(defaultOptions);
};

// Resmi kırpmak için yardımcı fonksiyon
export const getCroppedImageUrl = (publicId, width = 500, height = 500) => {
  return cld.image(publicId)
    .resize('fill', width, height)
    .toURL();
};

export default cld; 