import { useState, useEffect } from 'react';
import axios from 'axios';
import useAuth from "./useAuth";

const useAxiosFetch = (dataUrl) => {
    const [update, setUpdate] = useState('');
    const [data, setData] = useState([]);
    const [fetchError, setFetchError] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        let isMounted = true;
        const source = axios.CancelToken.source();

        const fetchData = async (url) => {
            setIsLoading(true);
            try {
                const response = await axios.get(url);
                if (isMounted) {
                    setData(response.data);
                    setFetchError(null);
                }
            } catch (err) {
                if (isMounted) {
                    setFetchError(err.message);
                    setData([]);
                }
            } finally {
                isMounted && setIsLoading(false);
            }
        }

        fetchData(dataUrl)

        const cleanUp = () => {
            isMounted = false;
            source.cancel();
        }

        return cleanUp;
    }, [update, dataUrl]);

    return { setUpdate, data, fetchError, isLoading };
}

export default useAxiosFetch;