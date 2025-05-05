import React, { useState } from 'react';
import axios from 'axios';

function AddCurrency() {
  const [currency, setCurrency] = useState({ code: '', englishName: '', chineseName: '' });

  const handleChange = e => {
    setCurrency({ ...currency, [e.target.name]: e.target.value });
  };

  const handleSubmit = e => {
    e.preventDefault();
    axios.post('http://localhost:8090/currency', currency)
      .then(() => window.location.href = '/')
      .catch(err => console.error(err));
  };

  return (
    <div>
      <h2>新增幣別</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>代碼:</label>
          <input type="text" name="code" value={currency.code} onChange={handleChange} required />
        </div>
        <div>
          <label>英文名稱:</label>
          <input type="text" name="englishName" value={currency.englishName} onChange={handleChange} />
        </div>
        <div>
          <label>中文名稱:</label>
          <input type="text" name="chineseName" value={currency.chineseName} onChange={handleChange} />
        </div>
        <button type="submit">新增</button>
      </form>
    </div>
  );
}

export default AddCurrency;
