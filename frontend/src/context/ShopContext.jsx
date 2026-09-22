import { createContext, useContext, useMemo, useState } from 'react';
import { sampleProducts } from '../data/products';

const ShopContext = createContext();

export function ShopProvider({ children }) {
  const [cart, setCart] = useState([]);
  const [favorites, setFavorites] = useState([]);
  const [lastOrder, setLastOrder] = useState(null);
  const [user, setUser] = useState({
    isLoggedIn: false,
    name: 'Jane Doe',
    email: 'jane@example.com'
  });

  const addToCart = (product, quantity = 1) => {
    setCart((current) => {
      const found = current.find((item) => item.id === product.id);
      if (found) {
        return current.map((item) =>
          item.id === product.id ? { ...item, quantity: item.quantity + quantity } : item
        );
      }
      return [...current, { ...product, quantity }];
    });
  };

  const updateCartQuantity = (productId, quantity) => {
    setCart((current) =>
      current
        .map((item) => (item.id === productId ? { ...item, quantity: Math.max(0, quantity) } : item))
        .filter((item) => item.quantity > 0)
    );
  };

  const removeFromCart = (productId) => {
    setCart((current) => current.filter((item) => item.id !== productId));
  };

  const clearCart = () => setCart([]);

  const toggleFavorite = (productId) => {
    setFavorites((current) =>
      current.includes(productId)
        ? current.filter((id) => id !== productId)
        : [...current, productId]
    );
  };

  const cartCount = cart.reduce((sum, item) => sum + item.quantity, 0);
  const cartTotal = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

  const value = useMemo(
    () => ({
      products: sampleProducts,
      cart,
      favorites,
      lastOrder,
      user,
      setUser,
      addToCart,
      updateCartQuantity,
      removeFromCart,
      clearCart,
      setLastOrder,
      toggleFavorite,
      cartCount,
      cartTotal
    }),
    [cart, favorites, user, lastOrder, cartCount, cartTotal]
  );

  return <ShopContext.Provider value={value}>{children}</ShopContext.Provider>;
}

export function useShop() {
  return useContext(ShopContext);
}
