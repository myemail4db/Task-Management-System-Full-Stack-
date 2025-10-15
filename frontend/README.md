# 🌐 **README.md for React/AngularJS Frontend**

# Task Management Frontend (React + AngularJS Demo)

This is a **frontend project** that interacts with the Task Management API (Spring Boot backend).  
It demonstrates both **modern React with Hooks** and **legacy AngularJS directives** for practice.

---

## 🚀 Features
- Fetch and display task list from backend API
- Update task status via API
- AngularJS demo with `ng-model`, `ng-repeat`, `ng-if`
- React demo with function components, state, and effects

---

## ⚙️ Tech Stack
- React 18 (with Hooks: `useState`, `useEffect`)
- AngularJS 1.8
- Axios for API calls
- HTML/CSS/Bootstrap

---

## 📂 Project Structure

```bash
frontend/
├── public/
│ └── index.html
└── src/
├── index.js                # React entry point
├── App.js                  # Main React component
├── TaskList.jsx            # React task list
├── UpdateStatus.jsx        # React status updater
└── angularjs-demo.html     # AngularJS demo page
```

---

## 🗂 Setup Instructions

### 1. React App

```bash
cd frontend
npm install
npm start
```

Runs at http://localhost:3000
Backend must be running at http://localhost:8080

### 2. AngularJS Demo

Open this file directly in a browser:

```bash
frontend/src/angularjs-demo.html
```

---

## 🔗 API Integration

Example React code with Axios:

```js
useEffect(() => {
  axios.get("http://localhost:8080/api/tasks")
    .then(response => setTasks(response.data.content))
    .catch(error => console.error(error));
}, []);
```

## 📖 Troubleshooting

CORS error → add this to frontend/package.json:

```json
"proxy": "http://localhost:8080"
```

- 404 on API → confirm backend is running and `/api/tasks` endpoint is available.

- React error: missing files → check `UpdateStatus.jsx` exists in `src/`.

## 📝 Notes

This frontend demonstrates:

- React Hooks (`useState`, `useEffect`)

- Props and state management

- Fetching data from backend API with Axios

- AngularJS basics (`ng-model`, `ng-repeat`, `ng-if`) for legacy understanding

---

✅ This gives you two polished README.md files:  
- `backend/README.md` → Spring Boot + MySQL API  
- `frontend/README.md` → React + AngularJS demo  

👉 Do you also want me to add a **root README.md** that links to both (`backend/README.md` and `frontend/README.md`) so everything ties together?

