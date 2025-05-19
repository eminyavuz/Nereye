import React from 'react';
import Header from '../components/Header';
import './Profile.css';

function Profile() {
  return (
    <>
      <Header />
      <div className="profile-container">
        <h2>User Profile</h2>
        <p>Name: John Doe</p>
        <p>Email: johndoe@example.com</p>
        <p>Member since: January 2023</p>
      </div>
    </>
  );
}

export default Profile;
