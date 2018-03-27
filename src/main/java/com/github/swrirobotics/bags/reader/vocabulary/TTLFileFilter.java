package com.github.swrirobotics.bags.reader.vocabulary;

import java.io.File;
import java.io.FileFilter;

public class TTLFileFilter implements FileFilter
    {
        private final String[] okFileExtensions = new String[] { "ttl" };

        public boolean accept(File file)
        {
            for (String extension : okFileExtensions)
            {
                if (file.getName().toLowerCase().endsWith(extension))
                {
                    return true;
                }
            }
            return false;
        }
    }

