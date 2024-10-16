#! /bin/sh

cat README.md \
    | sed '
        s_https://github.com/NivOridocs/burning_https://modrinth.com/mod/burning_'
