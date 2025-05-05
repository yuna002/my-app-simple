import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link, useNavigate } from 'react-router-dom';
import CurrencyList from './pages/CurrencyList';
import AddCurrency from './pages/AddCurrency';
import EditCurrency from './pages/EditCurrency';
import CoindeskView from './pages/CoindeskView';

function Navigation() {
  const [inputCode, setInputCode] = useState('');
  const navigate = useNavigate();

  const handleEditNavigate = () => {
    if (inputCode.trim()) {
      navigate(`/edit/${inputCode.trim().toUpperCase()}`);
    }
  };

  return (
    <nav>
      <ul>
        <li><Link to="/">幣別列表</Link></li>
        <li><Link to="/add">新增幣別</Link></li>
        <li><Link to="/coindesk">Coindesk 匯率資訊</Link></li>
      </ul>
    </nav>
  );
}

function App() {
  return (
    <Router>
      <div>
        <Navigation />
        <Routes>
          <Route path="/" element={<CurrencyList />} />
          <Route path="/add" element={<AddCurrency />} />
          <Route path="/edit/:code" element={<EditCurrency />} />
          <Route path="/coindesk" element={<CoindeskView />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
