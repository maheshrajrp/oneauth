/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
          sans: ['Inter', 'sans-serif'],
      },
      colors: {
        lightFont: '#A1A1A1',
        darkFont: '#0A0A0A',
        lightBg: '#1f1f1f',
      },
      padding: {
        'form': '0.5rem',
      },
    },
  },
  plugins: [],
}

