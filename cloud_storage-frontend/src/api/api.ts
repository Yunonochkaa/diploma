import httpClient from "@/api/httpClient";

/**
 * login (вход)
 * @param email
 * @param password
 */
const login = (email: string, password: string) => {
    return httpClient.post(
        '/login',
        {
            login: email,
            password
        }
    );
}

/**
 * logout (выход)
 */
const logout = () => {
    return httpClient.post(
        '/logout'
    );
}

/**
 * (get a list of files) получаем список файлов
 * @param limit
 */
const getFiles = (limit: number) => {
    return httpClient.get(
        `/list?limit=${limit}`,
    );
}

/**
 * add file (добавляем файл)
 */
const uploadFile = (file: any, filename: string) => {
    const formData = new FormData();
    formData.append('file', file);

    return httpClient.post(
        `/file?filename=${filename}`,
        formData,
        {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }
    );
}

/**
 * (download file) скачиваем файл
 */
const downloadFile = (filename: string) => {
    return httpClient.get(
        `/file?filename=${filename}`,
        {
            responseType: 'blob'
        }
    );
}

/**
 * (change file) изменяем файл
 */
const updateFile = (filename: string, fileData: any) => {
    return httpClient.put(
        `/file?filename=${filename}`,
        {
            ...fileData,
        }
    );
}

/**
 * (delete file ) удаляем файл
 */
const deleteFile = (filename: string) => {
    return httpClient.delete(
        `/file?filename=${filename}`,
    );
}

export {
    login,
    logout,
    getFiles,
    uploadFile,
    downloadFile,
    updateFile,
    deleteFile
}
