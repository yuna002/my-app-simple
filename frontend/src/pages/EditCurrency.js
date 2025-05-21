import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

function EditCurrency() {
  const { code } = useParams();
  const navigate = useNavigate();
  const [currency, setCurrency] = useState({
    code: '',
    englishName: '',
    chineseName: ''
  });

  useEffect(() => {
    axios.get(`http://localhost:8090/currency/${code}`)
      .then(res => setCurrency(res.data))
      .catch(err => console.error(err));
  }, [code]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCurrency(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.put(`http://localhost:8090/currency/${code}`, currency)
      .then(() => navigate('/'))
      .catch(err => console.error(err));
  };

  return (
    <div>
      <h2>編輯幣別</h2>
      <form onSubmit={handleSubmit}>
        <label>
          幣別代碼:
          <input type="text" name="code" value={currency.code} disabled />
        </label><br />
        <label>
          英文名稱:
          <input type="text" name="englishName" value={currency.englishName} onChange={handleChange} />
        </label><br />
        <label>
          中文名稱:
          <input type="text" name="chineseName" value={currency.chineseName} onChange={handleChange} />
        </label><br />
        <button type="submit">送出</button>
      </form>
    </div>
  );
}

export default EditCurrency;
