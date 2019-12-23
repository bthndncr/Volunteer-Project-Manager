export default {
    saveProfile(profile) {
        localStorage.setItem('profile', JSON.stringify(profile));
    },

    destroyProfile() {
        localStorage.removeItem('profile');
    },

    getProfile() {
        try {
            return JSON.parse(localStorage.getItem('profile'));
        } catch {
            return null;
        }
    }
}
