from setuptools import setup, find_packages
from glob import glob

setup(
    name='collare',
    version='1.3',
    author='Martin Petran',
    license='Apache 2.0',
    description='Multi-tool collaboration for reverse engineers.',
    url='https://github.com/Martyx00/CollaRE',
    download_url='https://github.com/Martyx00/CollaRE/archive/refs/tags/v1.3.tar.gz',
    packages = ['collare'],
    package_data = {'collare' : ["icons/*"] },
    entry_points={
        'console_scripts': [
            'collare=collare.collare:main',
        ]
    },
    install_requires=[
        'PyQt5==5.15.1',
        'requests'
    ]
)

