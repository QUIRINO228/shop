class StorageUtil {
  setToken(accessToken: string) {
    if (!process.browser) {
      return;
    }

    localStorage.setItem("ACCESS_TOKEN", accessToken);
  }

  getAccessToken(): string | null {
    if (!process.browser) {
      return null;
    }

    return localStorage.getItem("ACCESS_TOKEN") || null;
  }

  removeToken() {
    if (!process.browser) {
      return;
    }

    localStorage.removeItem("ACCESS_TOKEN");
  }
}

export default new StorageUtil();
